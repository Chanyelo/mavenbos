package cn.itcast.bos.web.action.user;

import cn.itcast.bos.service.user.UserService;
import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.web.action.base.BaseAction;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sonner or Later on 2017/5/10.
 */
@ParentPackage("basic-bos")
@Namespace("/")
@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction<User> {
    //注入service
    @Autowired
    private UserService userService;

    @Action(value = "user_login",results = {//@Result(name = "login",location = "/login.jsp"),
                                            @Result(name = "success",location = "/index.jsp",type = "redirect")})
    @InputConfig(resultName = "login") //更改错误检验的结果集
    public String login(){
        //验证码校验
        String checkcode = super.getParameter("checkcode");
        //session中的验证码
        String checkcode_session = (String) super.getSessionAttribute("key");
        //取出验证码就清楚点
        super.removeFromSeesion("key");
        //判断
        if(StringUtils.isBlank(checkcode_session) || !checkcode_session.equalsIgnoreCase(checkcode)){
            //检验失败
            this.addFieldError("checkcode",this.getText("UserAction.checkcodeerror"));
            //跳转到登陆页面
            return LOGIN;
        }
        //登录逻辑
        User user = userService.login(model);
        if(null == user){
            //登陆失败
            this.addActionError(this.getText("UserAction.loginuser"));
            return LOGIN;
        }else{
            //登陆成功
            super.setSessionAttribute("user",user);
            return SUCCESS;
        }
    }

    //注销
    @Action(value = "user_logout",results = {@Result(name = "success",type = "redirect",location = "login.jsp")})
    public String logout(){
        ServletActionContext.getRequest().getSession().invalidate();
        return SUCCESS;
    }

    //修改密码
    @Action(value = "user_editpassword",results = {@Result(name = JSON,type = JSON)})
    public String editpassword(){
        //获取当前登陆的用户
        User user = (User) super.getSessionAttribute("user");
        //model封装了id和密码
        model.setId(user.getId());
        //页面结果反馈json
        Map<String,Object> resultMap = new HashMap<>();
        try {
            //调用业务层修改
            userService.updateUserPasswordByUserId(model);
            resultMap.put("result",true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result",false);
        }
        //转换到json对象，压入栈顶即可。
        super.pushToValuestackRoot(resultMap);
        return JSON;
    }

}
