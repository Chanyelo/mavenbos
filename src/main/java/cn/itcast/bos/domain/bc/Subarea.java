package cn.itcast.bos.domain.bc;

import javax.persistence.*;

/**
 * Created by Sonner or Later on 2017/5/12.
 */
@Entity
@Table(name = "T_BC_SUBAREA", schema = "BOS", catalog = "")
public class Subarea {
    private String id;
    private String addresskey;
    private String startnum;
    private String endnum;
    private String single;
    private String position;
    private Decidedzone tBcDecidedzoneByDecidedzoneId;
    private Region region;

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ADDRESSKEY")
    public String getAddresskey() {
        return addresskey;
    }

    public void setAddresskey(String addresskey) {
        this.addresskey = addresskey;
    }

    @Basic
    @Column(name = "STARTNUM")
    public String getStartnum() {
        return startnum;
    }

    public void setStartnum(String startnum) {
        this.startnum = startnum;
    }

    @Basic
    @Column(name = "ENDNUM")
    public String getEndnum() {
        return endnum;
    }

    public void setEndnum(String endnum) {
        this.endnum = endnum;
    }

    @Basic
    @Column(name = "SINGLE")
    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    @Basic
    @Column(name = "POSITION")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subarea subarea = (Subarea) o;

        if (id != null ? !id.equals(subarea.id) : subarea.id != null) return false;
        if (addresskey != null ? !addresskey.equals(subarea.addresskey) : subarea.addresskey != null) return false;
        if (startnum != null ? !startnum.equals(subarea.startnum) : subarea.startnum != null) return false;
        if (endnum != null ? !endnum.equals(subarea.endnum) : subarea.endnum != null) return false;
        if (single != null ? !single.equals(subarea.single) : subarea.single != null) return false;
        if (position != null ? !position.equals(subarea.position) : subarea.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (addresskey != null ? addresskey.hashCode() : 0);
        result = 31 * result + (startnum != null ? startnum.hashCode() : 0);
        result = 31 * result + (endnum != null ? endnum.hashCode() : 0);
        result = 31 * result + (single != null ? single.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "DECIDEDZONE_ID", referencedColumnName = "ID")
    public Decidedzone gettBcDecidedzoneByDecidedzoneId() {
        return tBcDecidedzoneByDecidedzoneId;
    }

    public void settBcDecidedzoneByDecidedzoneId(Decidedzone tBcDecidedzoneByDecidedzoneId) {
        this.tBcDecidedzoneByDecidedzoneId = tBcDecidedzoneByDecidedzoneId;
    }

    @ManyToOne
    @JoinColumn(name = "REGION_ID", referencedColumnName = "ID")
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
