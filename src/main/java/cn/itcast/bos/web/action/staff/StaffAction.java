package cn.itcast.bos.web.action.staff;

import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.service.Staff.StaffService;
import cn.itcast.bos.web.action.base.BaseAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sonner or Later on 2017/5/12.
 */
//取派员操作的action
@ParentPackage("basic-bos")
@Namespace("/")
@Controller("staffAction")
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
    //注入service
    @Autowired
    private StaffService staffService;

    @Action(value = "staff_save",results = {@Result(name = "success",location = "/WEB-INF/pages/base/staff.jsp")})
    public String save(){
        //调用业务层保存数据
        staffService.saveStaff(model);
        return SUCCESS;
    }
    //列表分页查询
    @Action("staff_listpage")
    public String listpage(){
        //将分页请求的数据封装到Pageable对象中
        Pageable pageable = new PageRequest(page-1,rows);
        //调用业务层查询数据
        Page<Staff> pageRequest = staffService.listPageStaff(pageable);
        //使用json插件，将对象转换为json
        //super.pushToValuestackRoot(pageRequest);
        Map<String,Object> result = new HashMap<>();
        result.put("total",pageRequest.getTotalElements());
        result.put("rows",pageRequest.getContent());
        super.pushToValuestackRoot(result);
        return JSON;
    }

    //作废取派员
    @Action("staff_deleteBatch")
    public String deleteBatch(){
        //获取页面提交的ids参数值的字符串
        String ids = super.getParameter("ids");
        Map<String,Object> result = new HashMap<>();
        try {
            staffService.delateStaffBatch(ids);
            result.put("result",true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result",false);
        }
        pushToValuestackRoot(result);
        return JSON;
    }

    //属性驱动，封装请求的两个分页参数
    private int page;
    private int rows;

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
