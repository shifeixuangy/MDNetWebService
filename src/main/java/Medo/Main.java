package Medo;
import commonUtil.CommonVariable;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import java.util.Map;

/**
 * Created by liudongdong on 2015/4/7.
 */
public class Main {


    public static void main(final String[] args) throws Exception {
        final Session session = commonUtil.DBUtil.getSession(CommonVariable.DEFAULT_PROJID);
        try {
//            session.beginTransaction();
//            data.TbDbAttri db=new TbDbAttri();
//            db.setDbid("dbid1");
//            session.save(db);
//            session.getTransaction().commit();
//            System.in.read();
        }
        catch (Exception ex)
        {
            session.getTransaction().rollback();
        }
        finally {

        }
    }
}
