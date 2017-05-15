package cn.itcast.bos.web.action.base;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Sonner or Later on 2017/5/10.
 */
//复用action代码
public  class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    protected T model;

    @Override
    public T getModel() {
        return model;
    }
    public BaseAction(){
        //对model进行实例化，通过子类的声明的泛型
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<T> modelClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        try {
            model = modelClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //向session中保存属性
    public void setSessionAttribute(String name,Object value){
        ServletActionContext.getRequest().getSession().setAttribute(name,value);
    }

    //从参数中获取值
    public String getParameter(String checkcode) {
        return ServletActionContext.getRequest().getParameter(checkcode);
    }

    //从session中获取值
    public Object getSessionAttribute(String key) {
        return ServletActionContext.getRequest().getSession().getAttribute(key);
    }

    //从session中清楚某一个对象
    public void removeFromSeesion(String key){
        ServletActionContext.getRequest().getSession().removeAttribute(key);
    }

    //值栈操作
    //压入root栈顶对象
    protected void pushToValuestackRoot(Object value) {
        ActionContext.getContext().getValueStack().push(value);
    }
    //压入root栈顶Map
    protected void setToValuestackRoot(String key,Object value){
        ActionContext.getContext().getValueStack().set(key, value);
    }
    //压入map栈
    protected void putToValuestackMap(String key,Object value){
        ActionContext.getContext().put(key, value);
    }

    //自定义常量
    public static final String JSON = "json";

    //属性驱动：封装请求的两个分页参数
    protected int page;//当前页码
    protected int rows;//每页最大记录数

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
