package services;

import commonUtil.AccessPermissions;
import commonUtil.AccessTokenPool;
import commonUtil.CommonVariable;
import commonUtil.ImageUtil;
import data.common.*;
import data.request.parameters.ResetMyPasswordParameter;
import data.request.parameters.SetUserHeadPhotoParameter;
import dataDAO.CommonQueryHelper;
import dataDAO.DataHelper;
import dataDAO.cache.CommonCache;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by liudongdong on 2015/4/27.
 */
public class CommonService {
    public String signIn(String account, String password,AccessType accessType) {
        TbUser user = CommonQueryHelper.getUser(account, password);
        if (user == null)
            return null;
        if(!AccessType.allowAccess(accessType,user))
            throw new RuntimeException("当前用户不允许此访问类型");
        UserInformation userInfo = new UserInformation(user.getId(),user.isAdmin(),LocalDateTime.now());
        userInfo.setAccessType(accessType);
        String accessToken = AccessTokenPool.getInstance().addToken(userInfo);
        return accessToken;
    }

    public void signOut(String accessToken) {
        AccessTokenPool.getInstance().deleteAccessToken(accessToken);
    }

    public TbUser getMyInfo(String accessToken) {
        UserInformation userInfo = AccessTokenPool.getInstance().getUserInformation(accessToken);
        TbUser user = userInfo.getUser().clone();
        user.setPassword(null);
        user.setIsOnLine(true);
        user.setLastOnLine(Timestamp.valueOf(userInfo.getTokenTime()));
        return user;
    }

    public String setUserHeadPhoto(SetUserHeadPhotoParameter pa,String accessToken)
    {
        TbUser user= AccessTokenPool.getInstance().getUserInformation(accessToken).getUser();
        if(user.getId()!=pa.getUserID())
        {
            if(!user.isAdmin())
                return null;
        }
        String imgPath= ImageUtil.addImage(pa);
        user.setHeadPhotoPath(imgPath);
        DataHelper.update(user,CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID);
        return imgPath;
    }

    public TbProjPower getMyPower(int projID,String accessToken)
    {
        UserInformation userInfo = AccessTokenPool.getInstance().getUserInformation(accessToken);
        int uid=userInfo.getUser().getId();
        Optional<TbProjManager>tpm=CommonCache.getCommonCache().getProjManagers().stream().filter(pm->(pm.getUid()==uid)&&(pm.getProjId()==projID)).findFirst();
        if(!tpm.isPresent())
            return null;
        Optional<TbProjPower>power=CommonCache.getCommonCache().getProjPowers().stream().filter(pp->pp.getId()==tpm.get().getPowerId()).findFirst();
        return power.isPresent()?power.get():null;
    }


    public void updateMyInfo(TbUser newUserInfo, String accessToken) {
        UserInformation userInformation=AccessTokenPool.getInstance().getUserInformation(accessToken);
        TbUser oldUserInfo =userInformation .getUser();
        newUserInfo.setLastOnLine(oldUserInfo.getLastOnLine());
        newUserInfo.setIsOnLine(oldUserInfo.getIsOnLine());
        newUserInfo.setPassword(oldUserInfo.getPassword());
        newUserInfo.setAccount(oldUserInfo.getAccount());
        newUserInfo.setAdmin(oldUserInfo.isAdmin());
        newUserInfo.setId(oldUserInfo.getId());
        newUserInfo.setAllowAccessType(oldUserInfo.getAllowAccessType());
        newUserInfo.setCompanyID(oldUserInfo.getCompanyID());
        newUserInfo.setHeadPhotoPath(oldUserInfo.getHeadPhotoPath());
        DataHelper.update(newUserInfo, CommonVariable.DEFAULT_PROJID);
//        userInformation.setUser(newUserInfo);
    }

    public Boolean resetMyPassword(ResetMyPasswordParameter pa,String accessToken)
    {
        TbUser user=AccessTokenPool.getInstance().getUserInformation(accessToken).getUser();
        if((pa.getCurrentPassword().equals(pa.getConfirmPassword()))&&(user.getPassword().equals(pa.getCurrentPassword())))
        {
            user.setPassword(pa.getNewPassword());
            DataHelper.update(user,CommonVariable.DEFAULT_PROJID);
            return true;
        }
        return false;
    }
}














