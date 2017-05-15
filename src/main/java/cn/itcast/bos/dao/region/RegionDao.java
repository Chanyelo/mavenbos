package cn.itcast.bos.dao.region;

import cn.itcast.bos.domain.bc.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Sonner or Later on 2017/5/13.
 */
//区域管理的dao接口
public interface RegionDao extends JpaRepository<Region,String> {
    //根据省市区等模糊匹配区域
    public List<Region> findByProvinceLikeOrCityLikeOrDistrictLikeOrCitycodeOrShortcode(String province,String city,String district,String citycode,String shortcode);
    @Query("from Region where province like ?1 or city like ?1 or district like ?1 or citycode=?2")
    public List<Region> findByProvinceLikeOrCityLikeOrDistrictLikeOrCitycode(String param1,String param2);
}


