package commonUtil;

import data.common.TbProjInfo;
import dataDAO.CommonQueryHelper;
import dataDAO.proj.interfaces.NativeSqlQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liudongdong on 2015/12/24.
 */
public class JDBCUtil {
    private static Log logger= LogFactory.getLog(JDBCUtil.class);
    private static Map<Integer,DBConfig>projDBConfigs=new HashMap<>();
    private static DBConfig dbConfig=DBConfig.getCommonConfig();

    static
    {
        try
        {
            //公共数据库
            projDBConfigs.put(CommonVariable.DEFAULT_AUTO_INCREMENT_ID,dbConfig);
            getProjConnStrUsingJDBC().entrySet().stream().forEach(kv->{
                DBConfig projDBConfig=DBConfig.valueOf(kv.getValue());
                projDBConfigs.put(kv.getKey(),projDBConfig);
            });
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(),ex);
        }
    }

    private static Map<Integer,String>getProjConnStrUsingJDBC()
    {
        Map<Integer,String>projConnStrings=new HashMap<>();
        try(Connection conn=getJDBCConnection(CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID))
        {
            try (Statement st=conn.createStatement()){
                ResultSet resultSet = st.executeQuery("select ID,ConnectString from tb_proj_info; ");
                while (resultSet.next()) {
                    projConnStrings.put(resultSet.getInt("ID"), resultSet.getString("ConnectString"));
                }
            }
            catch (Exception ex)
            {
                throw new RuntimeException(ex);
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
        return projConnStrings;
    }

    public static Connection getJDBCConnection(int projID)
    {
        DBConfig dbConfig=projDBConfigs.get(projID);
        if(dbConfig==null)
            return null;
        try {
            Connection c= DriverManager.getConnection(dbConfig.getMySqlString(), dbConfig.getUid(), dbConfig.getPwd());
            return c;
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    public static Map<Integer, DBConfig> getProjDBConfigs() {
        return projDBConfigs;
    }

}
