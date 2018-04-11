package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * Created by liudongdong on 2015/4/18.
 */
@Entity
@Table(name = "tb_warn_person", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbWarnPerson {
    @JsonProperty("ID")
    private int id;
    private String userName;
    private Short userWarnLevel;
    private String userPhone;
    private String userEmail;
    private Boolean onLine;
    private String position;
    private Integer companyID;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "UserName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "UserWarnLevel")
    public Short getUserWarnLevel() {
        return userWarnLevel;
    }

    public void setUserWarnLevel(Short userWarnLevel) {
        this.userWarnLevel = userWarnLevel;
    }

    @Basic
    @Column(name = "UserPhone")
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Basic
    @Column(name = "UserEmail")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Basic
    @Column(name = "OnLine")
    public Boolean getOnLine() {
        return onLine;
    }

    public void setOnLine(Boolean onLine) {
        this.onLine = onLine;
    }

    @Basic
    @Column(name = "Position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "CompanyID")
    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbWarnPerson that = (TbWarnPerson) o;

        if (id != that.id) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userWarnLevel != null ? !userWarnLevel.equals(that.userWarnLevel) : that.userWarnLevel != null)
            return false;
        if (userPhone != null ? !userPhone.equals(that.userPhone) : that.userPhone != null) return false;
        if (userEmail != null ? !userEmail.equals(that.userEmail) : that.userEmail != null) return false;
        if (onLine != null ? !onLine.equals(that.onLine) : that.onLine != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        return !(companyID != null ? !companyID.equals(that.companyID) : that.companyID != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userWarnLevel != null ? userWarnLevel.hashCode() : 0);
        result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (onLine != null ? onLine.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (companyID != null ? companyID.hashCode() : 0);
        return result;
    }
}
