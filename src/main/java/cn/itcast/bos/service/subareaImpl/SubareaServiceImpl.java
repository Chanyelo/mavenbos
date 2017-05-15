package cn.itcast.bos.service.subareaImpl;

import cn.itcast.bos.dao.subarea.SubareaDao;
import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.service.subarea.SubareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Sonner or Later on 2017/5/14.
 */
@Service("subareaService")
@Transactional
public class SubareaServiceImpl implements SubareaService {
    //注入dao
    @Autowired
    private SubareaDao subareaDao;
    @Override
    public void saveSubarea(Subarea subarea) {
        //调用dao
        subareaDao.save(subarea);
    }
}
