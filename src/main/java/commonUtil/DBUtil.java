package commonUtil;

import data.common.TbProjInfo;
import data.proj.DBInfo;
import data.proj.TbMonitorType;
import dataDAO.DataHelper;
import dataDAO.JDBCExecuteSql;
import dataDAO.impl.ProjectInfoQuery;
import dataDAO.interfaces.ObjectsQuery;
import dataDAO.proj.impl.DBInfoQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liudongdong on 2015/4/10.
 */
public class DBUtil {
    private static final SessionFactory commonSessionFactory;
    private static final Map<Integer, SessionFactory> projsSessionFactory = new HashMap<>();
    private static Log logger = LogFactory.getLog((DBUtil.class));

    static {
        try {
            DBConfig commConfig=DBConfig.getCommonConfig();
            commonSessionFactory=initSessionFactory(commConfig.getMySqlString(),commConfig.getUid(),commConfig.getPwd(),true);
            //为了方便，将commonSessionFactory也放入哈希表中
            projsSessionFactory.put(CommonVariable.DEFAULT_PROJID, commonSessionFactory);
            //初始化各个project的sessionFactory
            try {
                ObjectsQuery<TbProjInfo> query = new ProjectInfoQuery();
                query.query(null, CommonVariable.DEFAULT_PROJID).forEach(proj -> {
                    initProjSessionFactory(proj);
                });
            } catch (Exception ex) {
                logger.error("项目信息SessionFactory初始化错误", ex);
            }


        } catch (Throwable ex) {
            logger.error("hibernate数据库初始化错误。", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void initProjSessionFactory(TbProjInfo proj) {

        ConnectionStringExtract connExtract = new ConnectionStringExtract(proj.getConnectString());
        DBConfig projConfig=new DBConfig(connExtract.getServer(),connExtract.getDbName(),connExtract.getUid(),connExtract.getPwd());

        JDBCUtil.getProjDBConfigs().put(proj.getId(),projConfig);

        boolean isNeedCreateTable = false;
        if (!CreateDB.isDBExists(CommonVariable.DEFAULT_PROJID, connExtract.getDbName())) {
            CreateDB.createDB(CommonVariable.DEFAULT_PROJID, connExtract.getDbName());
            isNeedCreateTable = true;
        }

        SessionFactory projSessionFactory=initSessionFactory(projConfig.getMySqlString(),projConfig.getUid(),projConfig.getPwd(),false);
        projsSessionFactory.put(proj.getId(), projSessionFactory);

        if (isNeedCreateTable) {
            //写入监测类型
            CreateDB.createTable(proj.getId(), connExtract.getDbName());
            List<TbMonitorType>mTypes=TbMonitorType.defaultMonitorTypes();
            DataHelper.addBatch(mTypes,proj.getId());
            //写入db版本
            DBInfo di=new DBInfo();
            di.setDBVersion(CommonVariable.getCurrentDBVersion());
            DataHelper.addWithoutKey(di,proj.getId());
        }

    }


    public static SessionFactory getSessionFactory(int projID) {
        return projsSessionFactory.get(projID);
    }

    /**
     * @return 必须运行在事务中
     */
    public static Session getSession(int projID) {
        SessionFactory sFactory = getSessionFactory(projID);
        if (sFactory == null)
            throw new RuntimeException("没有获取到项目ID为：" + projID + "的SessionFactory");
        return sFactory.getCurrentSession();
    }

    public static int getSessionFactoryCount() {
        return projsSessionFactory.size();
    }

    private static SessionFactory initSessionFactory(String mysqlConn, String uid, String pwd, boolean isCommon) {
        Configuration pConfig = new Configuration();
        if (isCommon)
            pConfig.configure();
        else
            pConfig.configure(CommonVariable.PROJECTS_HIBERNATE_CONFIG_PATH);
        pConfig.setProperty("hibernate.connection.url", mysqlConn);
        pConfig.setProperty("hibernate.connection.username", uid);
        pConfig.setProperty("hibernate.connection.password", pwd);
        ServiceRegistry projServiceRegistry = new ServiceRegistryBuilder().applySettings(pConfig.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = pConfig.buildSessionFactory(projServiceRegistry);
        return sessionFactory;
    }

    public static void testInit()
    {}

    public static boolean isTableExists(int projID,String tableName)
    {
        try {
            Connection conn=JDBCUtil.getJDBCConnection(projID);
            try {
                DatabaseMetaData databaseMetaData = JDBCUtil.getJDBCConnection(projID).getMetaData();
                String dbName = conn.getSchema();
                ResultSet result= databaseMetaData.getTables(null, dbName, tableName, new String[]{"TABLE"});
                if(result.next())
                    return true;
                return false;
            }
            catch (Exception ex)
            {
                throw new RuntimeException(ex);
            }
            finally {
                if(conn!=null)
                    conn.close();
            }
        }
        catch (Exception ex)
        {throw new RuntimeException(ex);}
    }

    public static String getDBVersion(int projID)
    {
        DBInfoQuery dbInfoQuery=new DBInfoQuery();
        List<DBInfo>dbInfos= dbInfoQuery.query(null,projID);
        return dbInfos.size()>0?dbInfos.get(0).getDBVersion():null;
    }

    public static void setDBVersion(int projID,String oldDBVersion,String newDBVersion)
    {
        if(StringUtil.isNullOrEmpty(newDBVersion)||StringUtil.isNullOrEmpty(oldDBVersion))
            return;
        String sql=" UPDATE db_info  SET DB_Version='"+newDBVersion+"' WHERE DB_Version='"+oldDBVersion+"' ";
        JDBCExecuteSql.executeUpdate(projID,sql);
    }

}
