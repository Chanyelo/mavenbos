package cn.itcast.bos.service.subarea;

import cn.itcast.bos.domain.bc.Subarea;

/**
 * Created by Sonner or Later on 2017/5/14.
 */
//分区的业务层接口
public interface SubareaService {
    /**
     * 保存分区
     * @param model
     */
    public void saveSubarea(Subarea subarea);
}
