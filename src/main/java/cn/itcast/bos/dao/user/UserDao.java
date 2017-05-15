package cn.itcast.bos.dao.user;


import cn.itcast.bos.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Created by Sonner or Later on 2017/5/9.
 */
public interface UserDao extends JpaRepository<User, String> {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 根据用户名模糊查询
     * @param username
     * @return
     */
    public List<User> findByUsernameLike(String username);

    /**
     * 根据用户名查询密码
     * @param username
     * @return
     */
    //@Query(value = "select password from User where username=?")
    @Query("select password from User where username=?")
    //@Query(value = "select password from t_user where username = ?",nativeQuery = true)
    public String findPasswordByUsername(String username);

    //匿名参数占位符，按照索引
    @Query("from User where username = ? and password = ?") //hql
    public User findUserByUsernameAndPassword(String username,String password);
    //命名参数占位符，按照参数名称
    @Query("from User where username =:username and password =:pwd") //hql
    public User findUserByUsernameAndPassword1(@Param("pwd") String password,@Param("username") String username );
    @Query("from User where username = ?2 and password = ?1") //hql
    public User findUserByUsernameAndPassword2(String password,String username);

    @Query("update User set password = ?2 where id = ?1")
    @Modifying
    public void updateUserPassword(String id,String password);
}
