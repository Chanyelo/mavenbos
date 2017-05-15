package cn.itcast.bos.service.regionImpl;

import cn.itcast.bos.dao.region.RegionDao;
import cn.itcast.bos.domain.bc.Region;
import cn.itcast.bos.service.region.RegionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sonner or Later on 2017/5/13.
 */
//区域管理的业务层
    @Service("regionService")
    @Transactional
public class RegionServiceImpl implements RegionService {
        //注入dao
    @Autowired
    private RegionDao regionDao;
    @Override
    public void saveRegionBatch(List<Region> regionList) {
        //调用持久层批量保存
        regionDao.save(regionList);
    }

    @Override
    public Page<Region> findRegionPageList(Pageable pageable) {
        //调用持久层分页查询
        return regionDao.findAll(pageable);
    }

    @Override
    public List<Region> findRegionList() {
        return regionDao.findAll();
    }

    @Override
    public List<Region> findRegionListByParam(String param) {
        //判断条件是否存在
        //如果不存在就查询所有
        if (StringUtils.isBlank(param)) {
            return regionDao.findAll();
        } else {
            //条件存在，自动匹配省、市、区
            return regionDao.findByProvinceLikeOrCityLikeOrDistrictLikeOrCitycode("%"+param+"%",param);
        }
    }

}
