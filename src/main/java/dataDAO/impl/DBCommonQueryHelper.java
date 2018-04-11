package dataDAO.impl;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import data.common.*;
import data.request.parameters.GetLogListParameter;
import dataDAO.interfaces.ICommonQueryHelper;
import dataDAO.interfaces.ObjectsQuery;
import dataDAO.proj.impl.UniqueSqlQueryImpl;
import dataDAO.proj.interfaces.UniqueSqlQuery;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2015/6/8.
 */
public class DBCommonQueryHelper implements ICommonQueryHelper {
    public  TbUser getUser(String account, String password) {
        ObjectsQuery<TbUser> uq = new TbUserQuery();
        String where = "account='" + account + "' and password='" + password + "'";
        List<TbUser> results = uq.query(where, CommonVariable.DEFAULT_PROJID);
        if (results.size() > 0)
            return results.get(0);
        return null;
    }

    public  List<TbUser> getUser() {
        return getUser(null);
    }

    public  TbUser getUser(int uid) {
        List<TbUser> results = getUser(Arrays.asList(uid));
        return (results.size() > 0) ? results.get(0) : null;
    }

    public  List<TbUser> getUser(List<Integer> uids) {
        ObjectsQuery<TbUser> uq = new TbUserQuery();
        StringBuilder where = new StringBuilder();
        if ((uids != null) && (uids.size() > 0)) {
            if (uids.size() == 1) {
                where.append(" ID=" + Integer.toString(uids.get(0)));
            } else {
                where.append("ID in (");
                uids.forEach(i->{
                    where.append(i.toString()+",");
                });
                where.deleteCharAt(where.lastIndexOf(","));
                where.append(")   ");
            }
        }
        return uq.query(where.toString(),CommonVariable.DEFAULT_PROJID);
    }

    public  TbProjManager getUserProjManager(int uid) {
        ObjectsQuery<TbProjManager> pq = new TbProjManagerQuery();
        String where = "UID=" + Integer.toString(uid);
        List<TbProjManager> results = pq.query(where, CommonVariable.DEFAULT_PROJID);
        if (results.size() > 0)
            return results.get(0);
        return null;
    }

    public  List<TbProjManager> getProjManager() {
        ObjectsQuery<TbProjManager> pq = new TbProjManagerQuery();
        return pq.query(null, CommonVariable.DEFAULT_PROJID);
    }

    public  List<TbProjPower> getProjPower(int powerID) {
        ObjectsQuery<TbProjPower> pwQuery = new TbProjPowerQuery();
        if (powerID == (CommonVariable.DEFAULT_AUTO_INCREMENT_ID.intValue()))
            return pwQuery.query(null, CommonVariable.DEFAULT_PROJID);
        else
            return pwQuery.query("  ID=" + Integer.toString(powerID), CommonVariable.DEFAULT_PROJID);
    }

    public  TbProjInfo getProjInfo(int projID) {
        List<TbProjInfo> results = getProjInfo(Arrays.asList(projID));
        return results.size() > 0 ? results.get(0) : null;
    }

    public  List<TbProjInfo> getProjInfo() {
        return getProjInfo(null);
    }
    public  List<TbProjInfo>getProjInfo(List<Integer>projIDs)
    {
        ObjectsQuery<TbProjInfo> uq = new ProjectInfoQuery();
        StringBuilder where = new StringBuilder();
        if ((projIDs != null) && (projIDs.size() > 0)) {
            if (projIDs.size() == 1) {
                where.append(" ID=" + Integer.toString(projIDs.get(0)));
            } else {
                where.append("ID in (");
                projIDs.forEach(i->{
                    where.append(i.toString()+",");
                });
                where.deleteCharAt(where.lastIndexOf(","));
                where.append(")   ");
            }
        }
        return uq.query(where.toString(),CommonVariable.DEFAULT_PROJID);
    }

    public  List<TbRegionManager> getRegionManager() {
        ObjectsQuery<TbRegionManager> rmQuery = new TbRegionManagerQuery();
        return rmQuery.query(null, CommonVariable.DEFAULT_PROJID);

    }

    public  TbRegionManager getRegionManager(int regionID, int uid) {
        ObjectsQuery<TbRegionManager> rmQuery = new TbRegionManagerQuery();
        String where = "RegionID=" + Integer.toString(regionID) + "  and UID=" + Integer.toString(uid);
        List<TbRegionManager> results = rmQuery.query(where, CommonVariable.DEFAULT_PROJID);
        return results.size() > 0 ? results.get(0) : null;
    }

    public  int getLogCount(int uid, int projID, Timestamp begin, Timestamp end) {
        UniqueSqlQuery<Integer> uQuery = new UniqueSqlQueryImpl<>();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select count(1) as LogCount from tb_log where ");
        if(uid!=CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
        {
            sqlBuilder.append(" UID="+Integer.toString(uid)+"   ");
        }
        if(projID!=CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
        {
            if(sqlBuilder.indexOf("UID")>-1)
                sqlBuilder.append(" and ");
            sqlBuilder.append(" ProjID="+Integer.toString(projID)+"   ");
        }
        if((sqlBuilder.indexOf("UID")>-1)||(sqlBuilder.indexOf("ProjID")>-1))
            sqlBuilder.append(" and  ");
        sqlBuilder.append(" Time between '" + begin.toString() + "' and '" + end.toString() + "' ");
        Map<String, Type> columns = new HashMap<>();
        columns.put("LogCount", StandardBasicTypes.INTEGER);
        return uQuery.query(CommonVariable.DEFAULT_PROJID, sqlBuilder.toString(), columns);
    }

    public  List<TbLog> getLogList(GetLogListParameter pa) {
        ObjectsQuery<TbLog> logQuery = new TbLogQuery(pa.getPage(), pa.getPageSize());
        StringBuilder sqlBuilder = new StringBuilder();
        if (pa.getUid() != CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID) {
            sqlBuilder.append(" UID=" + Integer.toString(pa.getUid()) + "   ");
        }
        if (pa.getProjID() != CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID) {
            if (sqlBuilder.length() > 0)
                sqlBuilder.append(" and ");
            sqlBuilder.append(" ProjID=" + Integer.toString(pa.getProjID()));
        }
        if(sqlBuilder.length()>0)
            sqlBuilder.append("  and  ");
        sqlBuilder.append("  Time between '" + pa.getStartTime().toString() + "' and '" + pa.getEndTime().toString() + "' ");
        sqlBuilder.append(" order by Time  desc ");
        return logQuery.query(sqlBuilder.toString(), CommonVariable.DEFAULT_PROJID);
    }

    @Override
    public List<TbRegion> getRegion(List<Integer> regionIds) {
        ObjectsQuery<TbRegion>regionQuery=new TbRegionQuery();
        StringBuilder where = new StringBuilder();
        if ((regionIds != null) && (regionIds.size() > 0)) {
            if (regionIds.size() == 1) {
                where.append(" ID=" + Integer.toString(regionIds.get(0)));
            } else {
                where.append("ID in (");
                regionIds.forEach(i->{
                    where.append(i.toString()+",");
                });
                where.deleteCharAt(where.lastIndexOf(","));
                where.append(")   ");
            }
        }
        return regionQuery.query(where.toString(),CommonVariable.DEFAULT_PROJID);
    }

    @Override
    public List<TbCompany> getCompanyList(int companyID) {
        ObjectsQuery<TbCompany>companyQuery=new TbCompanyQuery();
        List<TbCompany>result=companyQuery.query(null,CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID);
        if(result==null)
            return Collections.emptyList();
        if(companyID!=CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            result=result.stream().filter(t->t.getId()==companyID).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<TbLevel> getLevelList() {
        ObjectsQuery<TbLevel>levelQuery=new TbLevelQuery();
        return levelQuery.query(null,CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID);
    }

    public List<TbProjType>getProjTypeList()
    {
        ObjectsQuery<TbProjType>projTypeQuery=new TbProjTypeQuery();
        return projTypeQuery.query(null,CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID);
    }
}
