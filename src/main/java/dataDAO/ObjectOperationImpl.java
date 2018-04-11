package dataDAO;

import commonUtil.CommonVariable;
import commonUtil.DBUtil;
import commonUtil.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by liudongdong on 2015/4/11.
 */
public class ObjectOperationImpl implements ObjectOperation {
    private static Log logger = LogFactory.getLog(ObjectOperationImpl.class);

    //    private Session session=DBUtil.getSession();
    @Override
    public Integer addObject(Object o, int projID) {
        Session session = DBUtil.getSession(projID);
        Integer id = CommonVariable.DEFAULT_AUTO_INCREMENT_ID;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Serializable seid = session.save(o);
            String sid = null;
            if (seid != null)
                sid = seid.toString();
            transaction.commit();
            if (!StringUtil.isNullOrEmpty(sid))
                id = Integer.valueOf(sid);
            return id;
        } catch (Exception ex) {
            if (transaction != null)
                transaction.rollback();
            throw ex;
        }
    }

    @Override
    public void addObjectWithoutKey(Object o, int projID) {
        Session session = DBUtil.getSession(projID);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(o);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null)
                transaction.rollback();
            throw ex;
        }
    }

    @Override
    public void addBatch(List<? extends Object> datas, int projID) {
        Session session = DBUtil.getSession(projID);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            datas.forEach(d -> {
                session.save(d);
            });
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null)
                transaction.rollback();
            throw ex;
        }
    }

    @Override
    public void updateObject(Object o, int projID) {
        Session session = DBUtil.getSession(projID);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(o);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null)
                transaction.rollback();
            throw ex;
        }
    }

    @Override
    public void saveOrUpdateObject(Object o, int projID) {
        Session session = DBUtil.getSession(projID);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(o);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null)
                transaction.rollback();
            throw ex;
        }
    }

    @Override
    public void deleteObject(Object o, int projID) {
        Session session = DBUtil.getSession(projID);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(o);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null)
                transaction.rollback();
            throw ex;
        }
    }
}
