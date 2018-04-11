package dataDAO.interfaces;

import data.common.*;
import data.request.parameters.GetLogListParameter;
import java.sql.Timestamp;
import java.util.List;


/**
 * Created by liudongdong on 2015/6/8.
 */
public interface ICommonQueryHelper {
    public  TbUser getUser(String account, String password);

    public  List<TbUser> getUser();

    public  TbUser getUser(int uid) ;

    public  List<TbUser> getUser(List<Integer> uids);

    public  TbProjManager getUserProjManager(int uid);

    public  List<TbProjManager> getProjManager();

    public  List<TbProjPower> getProjPower(int powerID);

    public  TbProjInfo getProjInfo(int projID);

    public  List<TbProjInfo> getProjInfo();
    public  List<TbProjInfo>getProjInfo(List<Integer>projIDs);

    public List<TbRegion>getRegion(List<Integer>regionIds);

    public  List<TbRegionManager> getRegionManager();

    public  TbRegionManager getRegionManager(int regionID, int uid);

    public  int getLogCount(int uid, int projID, Timestamp begin, Timestamp end);

    public  List<TbLog> getLogList(GetLogListParameter pa);

    List<TbCompany>getCompanyList(int companyID);

    List<TbLevel>getLevelList();

    List<TbProjType>getProjTypeList();
}
