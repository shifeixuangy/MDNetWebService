package dataDAO;

import commonUtil.CollectionUtil;
import commonUtil.DBUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * Created by liudongdong on 2015/5/5.
 */
public class ExecuteSql {

    public static void execute(int projID, String sql) {
        JDBCExecuteSql.executeUpdate(projID, sql);
//        Session session = null;
//        Transaction transaction=null;
//        try {
//            session = DBUtil.getSession(projID);
//            transaction= session.beginTransaction();
//            session.createSQLQuery(sql).executeUpdate();
//            transaction.commit();
//        } catch (Exception ex) {
//            if(transaction!=null)
//                transaction.rollback();
//            throw ex;
//        }
//        finally {
//            if(session!=null)
//                session.close();
//        }
    }

    public static void executeBatch(int projID,List<String> sqls)
    {
        SessionFactory sessionFactory = DBUtil.getSessionFactory(projID);
        ConnectionProvider connectionProvider = ((SessionFactoryImplementor) sessionFactory).getConnectionProvider();
        try (Connection conn = connectionProvider.getConnection()) {
            conn.setAutoCommit(false);
            try(Statement stmt=conn.createStatement())
            {
                sqls.forEach(s->{
                    try {
                        stmt.addBatch(s);
                    }catch (Exception ex)
                    {
                        throw new RuntimeException(ex);
                    }
                });
                stmt.executeBatch();
                conn.commit();
            }
            catch (Exception ex)
            {
                conn.rollback();
                throw ex;
            }
            conn.setAutoCommit(true);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }



}
