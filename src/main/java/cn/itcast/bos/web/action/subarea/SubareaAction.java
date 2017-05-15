package cn.itcast.bos.web.action.subarea;

import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.service.subarea.SubareaService;
import cn.itcast.bos.web.action.base.BaseAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by Sonner or Later on 2017/5/14.
 */
@ParentPackage("basic-bos")
@Controller("subareaAction")
@Namespace("/")
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
    //注入service
    @Autowired
    private SubareaService subareaService;
    @Action(value = "subarea_save",results = {@Result(name = "success",location = "/WEB-INF/pages/base/subarea.jsp")})
    public String save(){
        //调用业务层
        subareaService.saveSubarea(model);
        return SUCCESS;
    }
}
