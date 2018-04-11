package data.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import commonUtil.PathUtil;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/4/17.
 */
@Entity
@Table(name = "tb_user", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbUser {
    @JsonProperty("ID")
    private int id;
    private String account;
    private String name;
    private String password;
    private String company;
    private String department;
    private String position;
    private String email;
    private String cellPhone;
    private String phone;
    private String address;
    private Boolean isOnLine;
    private Timestamp lastOnLine;
    @JsonProperty("IsAdmin")
    private boolean isAdmin;
    private int allowAccessType;
    private Integer companyID;
    private String headPhotoPath;

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
    @Column(name = "Account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Company")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "Department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "CellPhone")
    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    @Basic
    @Column(name = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "IsOnLine")
    public Boolean getIsOnLine() {
        return isOnLine;
    }

    public void setIsOnLine(Boolean isOnline) {
        this.isOnLine = isOnline;
    }

    @Basic
    @Column(name = "LastOnLine")
    public Timestamp getLastOnLine() {
        return lastOnLine;
    }

    public void setLastOnLine(Timestamp lastOnLine) {
        this.lastOnLine = lastOnLine;
    }

    @Basic
    @Column(name = "IsAdmin")
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Basic
    @Column(name = "AllowAccessType")
    public int getAllowAccessType() {
        return allowAccessType;
    }

    public void setAllowAccessType(int allowAccessType) {
        this.allowAccessType = allowAccessType;
    }

    @Basic
    @Column(name = "CompanyID")
    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    @Basic
    @Column(name = "HeadPhotoPath")
    public String getHeadPhotoPath() {
        return headPhotoPath;
    }


    public void setHeadPhotoPath(String headPhotoPath) {
        this.headPhotoPath = headPhotoPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbUser user = (TbUser) o;

        if (id != user.id) return false;
        if (isAdmin != user.isAdmin) return false;
        if (allowAccessType != user.allowAccessType) return false;
        if (!account.equals(user.account)) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (!password.equals(user.password)) return false;
        if (company != null ? !company.equals(user.company) : user.company != null) return false;
        if (department != null ? !department.equals(user.department) : user.department != null) return false;
        if (position != null ? !position.equals(user.position) : user.position != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (cellPhone != null ? !cellPhone.equals(user.cellPhone) : user.cellPhone != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (isOnLine != null ? !isOnLine.equals(user.isOnLine) : user.isOnLine != null) return false;
        if (lastOnLine != null ? !lastOnLine.equals(user.lastOnLine) : user.lastOnLine != null) return false;
        if (companyID != null ? !companyID.equals(user.companyID) : user.companyID != null) return false;
        return !(headPhotoPath != null ? !headPhotoPath.equals(user.headPhotoPath) : user.headPhotoPath != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + account.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + password.hashCode();
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (cellPhone != null ? cellPhone.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (isOnLine != null ? isOnLine.hashCode() : 0);
        result = 31 * result + (lastOnLine != null ? lastOnLine.hashCode() : 0);
        result = 31 * result + (isAdmin ? 1 : 0);
        result = 31 * result + allowAccessType;
        result = 31 * result + (companyID != null ? companyID.hashCode() : 0);
        result = 31 * result + (headPhotoPath != null ? headPhotoPath.hashCode() : 0);
        return result;
    }

    public TbUser clone()
    {
        TbUser user=new TbUser();
        user.setId(this.id);
        user.setAccount(this.account);
        user.setName(this.name);
        user.setPassword(this.password);
        user.setCompany(this.company);
        user.setDepartment(this.department);
        user.setPosition(this.position);
        user.setEmail(this.email);
        user.setCellPhone(this.cellPhone);
        user.setPhone(this.phone);
        user.setAddress(this.address);
        user.setIsOnLine(this.isOnLine);
        user.setLastOnLine(this.lastOnLine);
        user.setAdmin(this.isAdmin());
        user.setAllowAccessType(this.allowAccessType);
        user.setCompanyID(this.companyID);
        user.setHeadPhotoPath(this.headPhotoPath);
        return user;
    }

    public TbUser withFullPathUser()
    {
        TbUser user=clone();
        if(user.getHeadPhotoPath()!=null)
        {
            user.setHeadPhotoPath(PathUtil.getPath(user.getHeadPhotoPath()));
        }
        return user;
    }
}
