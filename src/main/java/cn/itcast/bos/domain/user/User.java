package cn.itcast.bos.domain.user;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sonner or Later on 2017/5/9.
 */
//用户的实体类domain
    @Entity //实体标识
    @Table(name = "T_USER" ,schema = "bos")
    @NamedQueries({@NamedQuery(name="User.findPasswordByUsername",query="select password from User where username= ? ")})
public class User {
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator",strategy = "uuid")
    @Column(length = 32)
    private String id;
    @Column(length = 20,name = "username")
    private String username;
    @Column(length = 32)
    private String password;
    private Double salary;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(length = 10)
    private String gender;
    @Column(length = 40)
    private String station;
    @Column(length = 11)
    private String telephone;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "user{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salary=" + salary +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", station='" + station + '\'' +
                ", telephone='" + telephone + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
