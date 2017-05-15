package cn.itcast.bos.dao.subarea;

import cn.itcast.bos.domain.bc.Subarea;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Sonner or Later on 2017/5/14.
 */
//分区dao
public interface SubareaDao extends JpaRepository<Subarea,String> {
}
