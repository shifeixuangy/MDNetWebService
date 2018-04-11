package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.proj.TbWarnPerson;

/**
 * Created by liudongdong on 2015/5/9.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class AddAndUpdateWarnPersonParameter {
    private int projID;
    private String userName;
    private String userPhone;
    private String userEmail;
    private int userWarnLevel;
    private String position;
    private Integer companyID;



    public TbWarnPerson toTbWarnPerson()
    {
        TbWarnPerson wp=new TbWarnPerson();
        wp.setUserName(this.userName);
        wp.setUserPhone(this.userPhone);
        wp.setUserEmail(this.userEmail);
        wp.setUserWarnLevel((short)this.userWarnLevel);
        wp.setPosition(this.position);
        wp.setCompanyID(this.companyID);
        return  wp;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getUserWarnLevel() {
        return userWarnLevel;
    }

    public void setUserWarnLevel(int userWarnLevel) {
        this.userWarnLevel = userWarnLevel;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }
}
