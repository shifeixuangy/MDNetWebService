package data.common;

import commonUtil.CommonVariable;
import dataDAO.CommonQueryHelper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2015/4/19.
 * 用户信息，权限，项目的封装，线程安全
 */
public class UserInformation {
    private TbUser user;
    private Integer accessLevel;
    private int projID;
    private boolean isAdmin;
    private LocalDateTime tokenTime;
    private AccessType accessType;


    private int uid;

    public UserInformation()
    {}

    public UserInformation(TbUser user,Integer accessLevel,boolean isAdmin,LocalDateTime tokenTime)
    {
        this.user=user;
        this.accessLevel=accessLevel;
        this.isAdmin=isAdmin;
        this.tokenTime=tokenTime;
    }
    public UserInformation(int uid,boolean isAdmin,LocalDateTime tokenTime)
    {
        this.uid=uid;
        this.isAdmin=isAdmin;
        this.tokenTime=tokenTime;
    }


    public synchronized List<Integer>getUserProjIDs()
    {
        if(this.isAdmin)
            return CommonQueryHelper.getProjInfo().stream().map(pi -> pi.getId()).collect(Collectors.toList());
        else
            return CommonQueryHelper.getProjManager().stream().filter(pm->pm.getUid()==this.uid).map(pm->pm.getProjId()).collect(Collectors.toList());
    }

    public synchronized TbUser getUser() {
        return CommonQueryHelper.getUser(this.uid);
    }


    public synchronized Integer getAccessLevel() {
        return accessLevel;
    }
    //如果用户不是管理员，则获取用户在项目中的权限，如无权限，则为Public
    public synchronized Integer getAccessLevel(int projID)
    {
        if(this.isAdmin)
            return Permission.ADMIN;
        List<TbProjManager> projManagers = CommonQueryHelper.getUserAllProjManager(this.uid);
        if (projManagers == null)
            return Permission.RECOGNIZE_USER;
        //查询用户在某个项目的权限
        if(projID!= CommonVariable.DEFAULT_PROJID) {
            Optional<TbProjManager> projManager = projManagers.stream().filter(tp -> tp.getProjId() == projID).findFirst();
            if (!projManager.isPresent())
                return Permission.RECOGNIZE_USER;
            return projManager.get().getPowerId();
        }
        //查询用户在-1项目的权限
        else
        {
            if(projManagers.size()==0)
                return Permission.RECOGNIZE_USER;
            OptionalInt oi= projManagers.stream().mapToInt(pm->pm.getPowerId()).max();
            return  oi.isPresent()?oi.getAsInt():Permission.RECOGNIZE_USER;
        }
    }


    public synchronized boolean isAdmin() {
        return isAdmin;
    }


    public synchronized LocalDateTime getTokenTime() {
        return tokenTime;
    }

    public synchronized void setTokenTime(LocalDateTime tokenTime) {
        this.tokenTime = tokenTime;
    }

    public synchronized int getUid() {
        return uid;
    }

    public synchronized AccessType getAccessType() {
        return accessType;
    }

    public synchronized void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }
}
