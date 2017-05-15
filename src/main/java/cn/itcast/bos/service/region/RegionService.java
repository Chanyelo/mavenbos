package cn.itcast.bos.service.region;

import cn.itcast.bos.domain.bc.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Sonner or Later on 2017/5/13.
 */
//区域管理的业务接口
public interface RegionService {

    /**
     * 批量保存区域
     * @param regionList
     */
    public void saveRegionBatch(List<Region> regionList);

    /**
     * 分页查询区域列表
     * @param pageable
     * @return
     */
    public Page<Region> findRegionPageList(Pageable pageable);

    /**
     * 查询数据列表
     * @return
     */
    public List<Region> findRegionList();

    /**
     * 通过参数模糊查询
     * @param q
     * @return
     */
    public List<Region> findRegionListByParam(String q);
}
