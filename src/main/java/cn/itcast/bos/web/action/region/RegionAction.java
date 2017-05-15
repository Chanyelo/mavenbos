package cn.itcast.bos.web.action.region;

import cn.itcast.bos.domain.bc.Region;
import cn.itcast.bos.service.region.RegionService;
import cn.itcast.bos.utils.PinYin4jUtils;
import cn.itcast.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.itcast.bos.utils.FileUtils.encodeDownloadFilename;

/**
 * Created by Sonner or Later on 2017/5/13.
 */
//区域管理的action
@ParentPackage("basic-bos")
@Namespace("/")
@Controller("regionAction")
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
    //注入service
    @Autowired
    private RegionService regionService;
    //用来接收用来上传的文件
    private File upload;
    private String uploadFilename;
    private String uploadContentType;

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadFilename(String uploadFilename) {
        this.uploadFilename = uploadFilename;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    @Action("region_importData")
    public String importData() {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            //文件上传后解析导入代码
            //打开Excel工作簿文件
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(upload));
            //打开需要解析的sheet工作表
            //读取第一个工作表
            XSSFSheet sheet = workbook.getSheetAt(0);
            List<Region> regionList = new ArrayList<>();
            //遍历工作表对象，读取每一行
            for (Row row : sheet) {
                //过滤第一行
                if (row.getRowNum() == 0) {
                    continue;
                }
                if (StringUtils.isNotBlank(row.getCell(0).getStringCellValue())) {
                    Region region = new Region();
                    //省市区简码和城市编码
                    String province = row.getCell(1).getStringCellValue();
                    String city = row.getCell(2).getStringCellValue();
                    String district = row.getCell(3).getStringCellValue();
                    region.setId(row.getCell(0).getStringCellValue());
                    //去掉最后一个字
                    province.substring(0, province.length() - 1);
                    city.substring(0, city.length() - 1);
                    district.substring(0, district.length() - 1);
                    //生成简码
                    String[] headArray = PinYin4jUtils.getHeadByString(province + city + district);
                    String shortcode = StringUtils.join(headArray);
                    region.setShortcode(shortcode);
                    //生成城市简码
                    String citycode = PinYin4jUtils.hanziToPinyin(city, "");
                    region.setCitycode(citycode);
                    region.setProvince(province);
                    region.setCity(city);
                    region.setDistrict(district);
                    region.setPostcode(row.getCell(4).getStringCellValue());

                    regionList.add(region);
                }
            }
            //调用业务层批量保存区域代码
            regionService.saveRegionBatch(regionList);
            resultMap.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", false);
        }
//        将结果压入值栈
        pushToValuestackRoot(resultMap);
        return JSON;
    }

    //分页列表查询
    @Action("region_listpage")
    public String listpage() {
        //将请求的两个参数封装到pageable
        Pageable pageable = new PageRequest(page - 1, rows);
        //调用业务层方法
        Page<Region> page = regionService.findRegionPageList(pageable);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", page.getTotalElements());//总条数
        resultMap.put("rows", page.getContent());//每页数据
        //压入栈顶
        pushToValuestackRoot(resultMap);
        return JSON;
    }

    //导出所有区域数据
    @Action("region_exportData")
    public String exportData() throws Exception {
        //新建一个空的工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //在工作簿上创建工作表
        XSSFSheet sheet = workbook.createSheet("区域数据");
        //在工作表上写入第一行
        XSSFRow headerRow = sheet.createRow(0);
        //写头格
        headerRow.createCell(0).setCellValue("区域编号");
        headerRow.createCell(1).setCellValue("省份");
        headerRow.createCell(2).setCellValue("城市");
        headerRow.createCell(3).setCellValue("区域");
        headerRow.createCell(4).setCellValue("邮编");
        //查询数据列表
        List<Region> regionList = regionService.findRegionList();
        //非空校验
        if (regionList != null && !regionList.isEmpty()) {
            for (Region region : regionList) {
                //创建下一行
                XSSFRow sheetRow = sheet.createRow(sheet.getLastRowNum() + 1);
                //写入数据
                sheetRow.createCell(0).setCellValue(region.getId());
                sheetRow.createCell(1).setCellValue(region.getPostcode());
                sheetRow.createCell(2).setCellValue(region.getCity());
                sheetRow.createCell(3).setCellValue(region.getDistrict());
                sheetRow.createCell(4).setCellValue(region.getPostcode());
            }
        }
        //设置下载的两个参数。将excel写入响应即可
        HttpServletResponse response = ServletActionContext.getResponse();
        //设置客户端用于识别附件的两个参数Content-Type和Content-Disposition
        //文件名
        String downFilename = "区域数据.xlsx";
        //获取文件的MIME类型
        String mimeType = ServletActionContext.getServletContext().getMimeType(downFilename);
        //将MIME放入到响应中
        response.setContentType(mimeType);
        //浏览器类型
        String agent = ServletActionContext.getRequest().getHeader("user-agent");
        //附件名编码，解决中文乱码问题
        downFilename = encodeDownloadFilename(downFilename, agent);
        //获取附件的名字和下载方式
        String contentDisposition = "attachment;filename=" + downFilename;
        //将附件名字和下载方式放入响应头信息中
        response.setHeader("Content-Disposition", contentDisposition);
        //将Excel文件写入到响应
        workbook.write(response.getOutputStream());
        return NONE;
    }

    //异步请求区域列表
    @Action("region_listajax")
    public String listajax(){
        //获取comboxbox传过来的参数
        String q = super.getParameter("q");
        //调用业务层方法
        //List<Region> list = regionService.findRegionList();
        List<Region> list = regionService.findRegionListByParam(q);
        //压入值栈
        pushToValuestackRoot(list);
        return JSON;
    }

}
