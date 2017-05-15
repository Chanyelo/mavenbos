package cn.itcast.bos.service.userImpl;

import cn.itcast.bos.service.user.UserService;
import cn.itcast.bos.dao.user.UserDao;
import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户管理的业务层实现类
 * Created by Sonner or Later on 2017/5/9.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    //注入dao
    @Autowired
    private UserDao userDao;

    @Override
    public void saveUser(User user) {
        //密码加密
        user.setPassword(MD5Utils.md5(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAll();
    }

    @Override
    public String findPasswordByUsername(String username) {
        return userDao.findPasswordByUsername(username);
    }


    @Override
    public User login(User user) {
        System.out.println(MD5Utils.md5(user.getPassword()));
        return userDao.findUserByUsernameAndPassword(user.getUsername(),MD5Utils.md5(user.getPassword()));

    }

    @Override
    public void updateUserPasswordByUserId(User user) {
        userDao.updateUserPassword(user.getId(),MD5Utils.md5(user.getPassword()));
    }
}
