package cn.itcast.bos.service.user;

import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.utils.MD5Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/** 
* UserServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>05/09/2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceImplTest {
    //注入测试
    @Autowired
    private UserService userService;

/**
* 
* Method: saveUser(User user) 
* 
*/ 
@Test
public void testSaveUser() throws Exception {
    User user = new User();
    user.setUsername("admin");
    user.setPassword(MD5Utils.md5("admin"));
    userService.saveUser(user);
} 

/** 
* 
* Method: findAllUser() 
* 
*/ 
@Test
public void testFindAllUser() throws Exception {
    List<User> list = userService.findAllUser();
    System.out.println(list);
}

    @Test
    public void testFindPasswordByUsername() throws Exception {
        System.out.println(userService.findPasswordByUsername("admin"));
    }


} 
