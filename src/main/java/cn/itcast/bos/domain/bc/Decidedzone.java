package cn.itcast.bos.domain.bc;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Sonner or Later on 2017/5/12.
 */
@Entity
@Table(name = "T_BC_DECIDEDZONE", schema = "BOS", catalog = "")
public class Decidedzone {
    private String id;
    private String name;
    private Staff tBcStaffByStaffId;
    private Collection<Subarea> tBcSubareasById;

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Decidedzone that = (Decidedzone) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "STAFF_ID", referencedColumnName = "ID")
    public Staff gettBcStaffByStaffId() {
        return tBcStaffByStaffId;
    }

    public void settBcStaffByStaffId(Staff tBcStaffByStaffId) {
        this.tBcStaffByStaffId = tBcStaffByStaffId;
    }

    @OneToMany(mappedBy = "tBcDecidedzoneByDecidedzoneId")
    public Collection<Subarea> gettBcSubareasById() {
        return tBcSubareasById;
    }

    public void settBcSubareasById(Collection<Subarea> tBcSubareasById) {
        this.tBcSubareasById = tBcSubareasById;
    }
}
