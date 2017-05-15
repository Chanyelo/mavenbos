package cn.itcast.bos.service.StaffImpl;

import cn.itcast.bos.dao.staff.StaffDao;
import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.service.Staff.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Sonner or Later on 2017/5/12.
 */
//取派员操作的service实现
@Service("staffService")
@Transactional
public class StaffServiceImpl implements StaffService {
    //注入dao
    @Autowired
    private StaffDao staffDao;
    @Override
    public void saveStaff(Staff staff) {
        //调用dao保存数据
        staffDao.save(staff);
    }

    @Override
    public Page<Staff> listPageStaff(Pageable pageable) {
        return staffDao.findAll(pageable);
    }

    @Override
    public void delateStaffBatch(String ids) {
        String[] idArr = ids.split(",");
        for (String id :idArr) {
            staffDao.updateDeltagById(id,'1');
        }
    }
}
