package cn.itcast.bos.domain.bc;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Sonner or Later on 2017/5/12.
 */
@Entity
@Table(name = "T_BC_STAFF", schema = "BOS", catalog = "")
public class Staff {
    private String id;
    private String name;
    private String telephone;
    private Character haspda = '0';
    private Character deltag = '0';
    private String station;
    private String standard;
    private Collection<Decidedzone> tBcDecidedzonesById;

    @Id
    @GenericGenerator(name = "generator" ,strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "TELEPHONE")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "HASPDA")
    public Character getHaspda() {
        return haspda;
    }

    public void setHaspda(Character haspda) {
        this.haspda = haspda;
    }

    @Basic
    @Column(name = "DELTAG")
    public Character getDeltag() {
        return deltag;
    }

    public void setDeltag(Character deltag) {
        this.deltag = deltag;
    }

    @Basic
    @Column(name = "STATION")
    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Basic
    @Column(name = "STANDARD")
    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        if (id != null ? !id.equals(staff.id) : staff.id != null) return false;
        if (name != null ? !name.equals(staff.name) : staff.name != null) return false;
        if (telephone != null ? !telephone.equals(staff.telephone) : staff.telephone != null) return false;
        if (haspda != null ? !haspda.equals(staff.haspda) : staff.haspda != null) return false;
        if (deltag != null ? !deltag.equals(staff.deltag) : staff.deltag != null) return false;
        if (station != null ? !station.equals(staff.station) : staff.station != null) return false;
        if (standard != null ? !standard.equals(staff.standard) : staff.standard != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (haspda != null ? haspda.hashCode() : 0);
        result = 31 * result + (deltag != null ? deltag.hashCode() : 0);
        result = 31 * result + (station != null ? station.hashCode() : 0);
        result = 31 * result + (standard != null ? standard.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tBcStaffByStaffId")
    @JSON(serialize = false)
    public Collection<Decidedzone> gettBcDecidedzonesById() {
        return tBcDecidedzonesById;
    }

    public void settBcDecidedzonesById(Collection<Decidedzone> tBcDecidedzonesById) {
        this.tBcDecidedzonesById = tBcDecidedzonesById;
    }
}
