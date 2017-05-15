package cn.itcast.bos.service.Staff;

import cn.itcast.bos.domain.bc.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Sonner or Later on 2017/5/12.
 */
//取派送员的service接口
public interface StaffService {
    /**
     * 保存派送员
     * @param model
     */
    public void saveStaff(Staff model);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    public Page<Staff> listPageStaff(Pageable pageable);

    /**
     * 批量废除取派员
     * @param ids
     */
    public void delateStaffBatch(String ids);
}
