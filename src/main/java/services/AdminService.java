package services;

import commonUtil.CommonVariable;
import commonUtil.StringUtil;
import data.common.TbProjManager;
import data.common.TbUser;
import data.request.parameters.AddUserInfoParameter;
import data.response.results.GetProjUserListResult;
import dataDAO.CommonQueryHelper;
import dataDAO.DataHelper;
import dataDAO.cache.CommonCacheCheck;

import java.util.List;

/**
 * Created by liudongdong on 2015/4/28.
 */
public class AdminService {
    public List<TbUser>getUserList()
    {
        return CommonQueryHelper.getUser();
    }

    public List<GetProjUserListResult>getProjUserList(int projID)
    {
        return CommonQueryHelper.getProjUser(projID);
    }

    public Integer addUserInfo(AddUserInfoParameter userInfoPa)
    {
        TbUser addUser=new TbUser();
        addUser.setAccount(userInfoPa.getAccount());
        addUser.setPassword(userInfoPa.getPassword());
        addUser.setName(userInfoPa.getName());
        addUser.setCompany(userInfoPa.getCompany());
        addUser.setDepartment(userInfoPa.getDepartment());
        addUser.setPosition(userInfoPa.getPosition());
        addUser.setEmail(userInfoPa.getEmail());
        addUser.setCellPhone(userInfoPa.getCellPhone());
        addUser.setPhone(userInfoPa.getPhone());
        addUser.setAddress(userInfoPa.getAddress());
        addUser.setAllowAccessType(userInfoPa.getAllowAccessType());
        if((userInfoPa.getCompanyID()!=null)&&(userInfoPa.getCompanyID()!=0))
            addUser.setCompanyID(userInfoPa.getCompanyID());
        Integer uid= DataHelper.add(addUser, CommonVariable.DEFAULT_PROJID);
        return uid;
    }

    public void updateUserInfo(TbUser newUser)
    {
        TbUser oldUser=CommonQueryHelper.getUser(newUser.getId());
        oldUser.setName(newUser.getName());
        oldUser.setCompany(newUser.getCompany());
        oldUser.setDepartment(newUser.getDepartment());
        oldUser.setPosition(newUser.getPosition());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setCellPhone(newUser.getCellPhone());
        oldUser.setPhone(newUser.getPhone());
        oldUser.setAddress(newUser.getAddress());
        oldUser.setAllowAccessType(newUser.getAllowAccessType());
        oldUser.setCompanyID(newUser.getCompanyID());
        DataHelper.update(oldUser,CommonVariable.DEFAULT_PROJID);
    }

    public void resetUserPassword(int uid,String password)
    {
        TbUser user=CommonQueryHelper.getUser(uid);
        if(StringUtil.isNullOrEmpty(password)) {
            user.setPassword(CommonVariable.DEFAULT_USER_PASSWORD);
        }
        else
        {
            user.setPassword(password);
        }
        DataHelper.update(user,CommonVariable.DEFAULT_PROJID);
    }


    public void deleteUser(int uid)
    {
        TbUser user=new TbUser();
        user.setId(uid);
        DataHelper.delete(user,CommonVariable.DEFAULT_PROJID);
        CommonCacheCheck.checkObject(new TbProjManager(),CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID);
    }












}
