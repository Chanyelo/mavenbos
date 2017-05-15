package cn.itcast.bos.service.user;

import cn.itcast.bos.domain.user.User;

import java.util.List;

/**
 * 用户管理的业务接口
 * Created by Sonner or Later on 2017/5/9.
 */
public interface UserService {
    /**
     * 保存用户
     * @param user
     */
    public void saveUser(User user);

    /**
     * 查询所有用户信息列表
     * @return 用户列表
     */
    public List<User> findAllUser();

    /**
     * 根据用户名查询密码
     * @param username
     * @return
     */
    public String findPasswordByUsername(String username);

    /**
     * 登陆业务
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 根据id修改密码
     * @param user
     */
    public void updateUserPasswordByUserId(User user);
}
