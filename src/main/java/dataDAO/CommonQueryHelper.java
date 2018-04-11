package dataDAO;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import data.common.*;
import data.request.parameters.GetLogListParameter;
import data.response.results.GetProjUserListResult;
import dataDAO.cache.CommonCache;
import dataDAO.impl.*;
import dataDAO.interfaces.ObjectsQuery;
import dataDAO.proj.impl.UniqueSqlQueryImpl;
import dataDAO.proj.interfaces.UniqueSqlQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2015/4/27.
 */
public class CommonQueryHelper {
    private static Log logger = LogFactory.getLog(CommonQueryHelper.class);
    private static CommonCache commonCache = CommonCache.getCommonCache();

    public static TbUser getUser(String account, String password) {
        List<TbUser> users = commonCache.getUsers();
        if (users == null) {
            logger.error("从缓存中获取用户列表为空,从数据库中查询");
            ObjectsQuery<TbUser> uQuery = new TbUserQuery();
            users = uQuery.query(null, CommonVariable.DEFAULT_PROJID);
        }
        List<TbUser> uList = new LinkedList<>();
        users.forEach(t -> {
            if ((t.getAccount() == null) || (t.getPassword() == null))
                logger.info("数据库中有一个用户的用户名或者密码为NULL");
            else {
                if ((t.getAccount().equals(account) && (t.getPassword().equals(password))))
                    uList.add(t);
            }
        });
        return uList.size() > 0 ? uList.get(0) : null;
    }

    public static List<TbUser> getUser() {
        return commonCache.getUsers();
    }

    public static TbUser getUser(int uid) {
        Optional<TbUser> user = commonCache.getUsers().stream().filter(t -> t.getId() == uid).findFirst();
        return user.isPresent() ? user.get() : null;
    }

    public static List<TbUser> getUser(List<Integer> uids) {
        if (uids == null)
            return getUser();
        return commonCache.getUsers().stream().filter(t -> uids.contains(t.getId())).collect(Collectors.toList());
    }

    public static List<GetProjUserListResult> getProjUser(int projID) {
        List<GetProjUserListResult> result = new LinkedList<>();
        List<TbUser> users = getUser();
        if (CollectionUtil.isNullOrEmpty(users))
            return result;
        List<TbProjManager> managers = getProjManager();
        if (CollectionUtil.isNullOrEmpty(managers))
            return result;
        users.stream().forEach(u -> {
            Optional<TbProjManager> managerOptional = managers.stream().filter(pm -> pm.getUid() == u.getId() && pm.getProjId() == projID).findFirst();
            TbUser tempUser = null;
            if (managerOptional.isPresent() || u.isAdmin())
                tempUser = u;
            if(tempUser!=null)
            {
                GetProjUserListResult tempResult=new GetProjUserListResult(tempUser.getId(),tempUser.getName(),tempUser.isAdmin());
                result.add(tempResult);
            }
        });
        return result;
    }

    public static TbProjManager getUserProjManager(int uid) {
        Optional<TbProjManager> pm = commonCache.getProjManagers().stream().filter(tp -> tp.getUid() == uid).findFirst();
        return pm.isPresent() ? pm.get() : null;
    }

    public static List<TbProjManager> getUserAllProjManager(int uid) {
        return commonCache.getProjManagers().stream().filter(tp -> tp.getUid() == uid).collect(Collectors.toList());
    }

    public static List<TbProjManager> getProjManager() {
        return commonCache.getProjManagers();
    }

    public static TbProjManager getProjManager(int projID, int uid) {
        Optional<TbProjManager> pm = getProjManager().stream().filter(p -> (p.getUid() == uid) && (p.getProjId() == projID)).findFirst();
        return pm.isPresent() ? pm.get() : null;
    }

    public static List<TbProjPower> getProjPower(int powerID) {

        if (powerID == (CommonVariable.DEFAULT_AUTO_INCREMENT_ID.intValue()))
            return commonCache.getProjPowers();
        else
            return commonCache.getProjPowers().stream()
                    .filter(pp -> pp.getId() == powerID).collect(Collectors.toList());
    }

    public static TbProjInfo getProjInfo(int projID) {
        List<TbProjInfo> results = getProjInfo(Arrays.asList(projID));
        return results.size() > 0 ? results.get(0) : null;
    }

    public static List<TbProjInfo> getProjInfo() {
        return getProjInfo(null);
    }

    public static List<TbProjInfo> getProjInfo(List<Integer> projIDs) {
        if (projIDs == null)
            return commonCache.getProjInfos();
        return commonCache.getProjInfos().stream()
                .filter(pi -> projIDs.contains(pi.getId()))
                .collect(Collectors.toList());
    }

    public static List<TbRegionManager> getRegionManager() {
        return commonCache.getRegionManagers();
    }

    public static TbRegionManager getRegionManager(int regionID, int uid) {
        Optional<TbRegionManager> rm = commonCache.getRegionManagers().stream()
                .filter(r -> (r.getRegionId() == regionID) && (r.getUid() == uid))
                .findFirst();
        return rm.isPresent() ? rm.get() : null;
    }

    public static int getLogCount(int uid, int projID, Timestamp begin, Timestamp end, short logType) {
        UniqueSqlQuery<Integer> uQuery = new UniqueSqlQueryImpl<>();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select count(1) as LogCount from tb_log where ");
        if (uid != CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID) {
            sqlBuilder.append(" UID=" + Integer.toString(uid) + "  and  ");
        }
        if (projID != CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID) {
            sqlBuilder.append(" ProjID=" + Integer.toString(projID) + "  and  ");
        }
        int intLogType = (int) logType;
        if (intLogType != CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID) {
            sqlBuilder.append("  LogType=" + Short.toString(logType) + "  and  ");
        }
        sqlBuilder.append(" Time between '" + begin.toString() + "' and '" + end.toString() + "' ");
        Map<String, Type> columns = new HashMap<>();
        columns.put("LogCount", StandardBasicTypes.INTEGER);
        return uQuery.query(CommonVariable.DEFAULT_PROJID, sqlBuilder.toString(), columns);
    }

    public static List<TbLog> getLogList(GetLogListParameter pa) {
        ObjectsQuery<TbLog> logQuery = new TbLogQuery(pa.getPage(), pa.getPageSize());
        StringBuilder sqlBuilder = new StringBuilder();
        if (pa.getUid() != CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID) {
            sqlBuilder.append(" UID=" + Integer.toString(pa.getUid()) + "  and  ");
        }
        if (pa.getProjID() != CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID) {
            sqlBuilder.append(" ProjID=" + Integer.toString(pa.getProjID()) + "  and  ");
        }
        int logType = (int) pa.getLogType();
        if (logType != CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID) {
            sqlBuilder.append(" LogType=" + Integer.toString(logType) + "   and ");
        }
        sqlBuilder.append("  Time between '" + pa.getStartTime().toString() + "' and '" + pa.getEndTime().toString() + "' ");
        sqlBuilder.append(" order by Time  desc ");
        return logQuery.query(sqlBuilder.toString(), CommonVariable.DEFAULT_PROJID);
    }

    public static List<TbCompany>getCompanies(int companyID)
    {
        if(companyID==CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            return commonCache.getCompanies();
        return commonCache.getCompanies().stream().filter(c->c.getId()==companyID).collect(Collectors.toList());
    }

    public static List<TbLevel>getLevels(int levelID)
    {
        List<TbLevel>levels= commonCache.getLevels();
        if(levelID==CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            return levels;
        return levels.stream().filter(l->l.getId()==levelID).collect(Collectors.toList());
    }

    public static List<TbProjType>getProjTypes(int projTypeID)
    {
        List<TbProjType>projTypes=commonCache.getProjTypes();
        if(projTypeID==CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            return  projTypes;
        return projTypes.stream().filter(pt->pt.getId()==projTypeID).collect(Collectors.toList());
    }

    public static TbProjType getProjType(int projTypeID) {
        if (projTypeID == CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            return null;
        List<TbProjType> projTypes = getProjTypes(CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID);
        Optional<TbProjType> projTypeOptional = projTypes.stream().filter(pt -> pt.getId() == projTypeID).findFirst();
        return projTypeOptional.isPresent() ? projTypeOptional.get() : null;
    }
}














