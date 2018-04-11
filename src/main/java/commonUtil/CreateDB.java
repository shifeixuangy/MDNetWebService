package commonUtil;


import dataDAO.ExecuteSql;
import dataDAO.proj.impl.UniqueSqlQueryImpl;
import dataDAO.proj.interfaces.UniqueSqlQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by liudongdong on 2015/8/17.
 */
public class CreateDB {
    private static final Log logger = LogFactory.getLog(CreateDB.class);
    private static String initDBString=null;
    private static List<String>initTableString=new LinkedList<>();
    static
    {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("proj.sql"); InputStreamReader reader = new InputStreamReader(is)) {
            BufferedReader bReader = new BufferedReader(reader);
            StringBuilder sql = new StringBuilder();
            String lineSeparator = System.getProperty("line.separator", "/n");
            String s;
            while ((s = bReader.readLine()) != null) {
                //初始化数据库创建字符串
                if (s.toLowerCase().contains("create database")) {
                    initDBString=s;
                }
                //初始化表格创建字符串
                sql.append(s+lineSeparator);
            }
            String strSql=sql.toString();
            List<String> tempResult=Arrays.asList(strSql.split(";"));
            tempResult.forEach(ss->{
                initTableString.add(ss+";");
            });
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    public static List<String> getSqlStrings(String dbName) {
        List<String> result = new LinkedList<String>();
        try
        {
            initTableString.forEach(s->{
                String tempS=s.replace(DBConfig.getPROJ_DB_NAME(),dbName);
                result.add(tempS);
            });
            return  result;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getCreateDBString(String dbName) {
        try{
            return initDBString.replace(DBConfig.getPROJ_DB_NAME(),dbName);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static boolean isDBExists(int projID, String dbName) {
        UniqueSqlQuery<Integer> uq = new UniqueSqlQueryImpl<>();
        String sql = "select count(1) as dbcount from information_schema.schemata  where schema_name='" + dbName + "'";
        Map<String, Type> typeMap = new HashMap<>();
        typeMap.put("dbcount", StandardBasicTypes.INTEGER);
        int count = uq.query(projID, sql, typeMap);
        return count == 0 ? false : true;
    }

    public static void createDB(int projID, String dbName) {
        String sql = getCreateDBString(dbName);
        try {
            ExecuteSql.execute(projID, sql);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void createTable(int projID, String dbName) {
        List<String>sqls=getSqlStrings(dbName);
        ExecuteSql.executeBatch(projID,sqls);
//        SessionFactory sessionFactory = DBUtil.getSessionFactory(projID);
//        ConnectionProvider connectionProvider = ((SessionFactoryImplementor) sessionFactory).getConnectionProvider();
//        try (Connection conn = connectionProvider.getConnection()) {
//            conn.setAutoCommit(false);
//            try(Statement stmt=conn.createStatement())
//            {
//                sqls.forEach(s->{
//                    try {
//                        stmt.addBatch(s);
//                    }catch (Exception ex)
//                    {
//                        logger.error(ex.getMessage(),ex);
//                    }
//                });
//                stmt.executeBatch();
//                conn.commit();
//            }
//            catch (Exception ex)
//            {
//                conn.rollback();
//                throw ex;
//            }
//            conn.setAutoCommit(true);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
    }

}
