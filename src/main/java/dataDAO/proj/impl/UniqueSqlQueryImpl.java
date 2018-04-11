package dataDAO.proj.impl;

import commonUtil.DBUtil;
import dataDAO.proj.interfaces.UniqueSqlQuery;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import java.util.List;
import java.util.Map;

/**
 * Created by liudongdong on 2015/5/7.
 */
public class UniqueSqlQueryImpl<T> implements UniqueSqlQuery<T> {
    @Override
    public T query(int projID, String sql, Map<String, Type> columnTypes) {
        T results = null;
        Session session = null;
        Transaction transaction=null;
        try {
            session = DBUtil.getSession(projID);
            transaction= session.beginTransaction();
            SQLQuery sq = session.createSQLQuery(sql);
            columnTypes.forEach((s, t) -> {
                sq.addScalar(s, t);
            });
            results = (T) sq.uniqueResult();
            transaction.commit();
        } catch (Exception ex) {
            if(transaction!=null)
                transaction.rollback();
            throw ex;
        }
        return results;
    }
}
