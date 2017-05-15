package cn.itcast.bos.web.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

/**
 * Created by Sonner or Later on 2017/5/10.
 */

//登陆拦截器
    @Controller("loginInterceptor")
public class LoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        //判断session中是否有user信息
        if(null == ServletActionContext.getRequest().getSession().getAttribute("user")){
            //未登录
            return Action.LOGIN;
        }
        //登陆
        return invocation.invoke();//放行
    }
}
