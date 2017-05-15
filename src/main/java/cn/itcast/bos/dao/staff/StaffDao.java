package cn.itcast.bos.dao.staff;

import cn.itcast.bos.domain.bc.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Sonner or Later on 2017/5/12.
 */
//取派员操作的dao接口
public interface StaffDao extends JpaRepository<Staff,String> {

    @Query("UPDATE Staff set deltag = ?2 where id = ?1")
    @Modifying
    public void updateDeltagById(String id, Character deltag);
}
