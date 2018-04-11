package dataDAO.proj.impl;

import commonUtil.DBUtil;
import dataDAO.proj.interfaces.NativeSqlQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

/**
 * Created by liudongdong on 2015/4/30.
 */
public class NativeSqlQueryImpl<T> implements NativeSqlQuery<T> {
    private static final Log logger = LogFactory.getLog(NativeSqlQueryImpl.class);

    @Override
    public List<T> query(int projID, String sql, Class<T> tClass) {
        List<T> results;
        if(tClass==null)
            return Collections.emptyList();
        Session session = null;
        try {
            session = DBUtil.getSession(projID);
            session.beginTransaction();
            //执行的自动映射，注意如果返回的多个数据主键相同，则其只返回一个数据
            results = session.createSQLQuery(sql).addEntity(tClass).list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            if ((session != null) && (session.getTransaction() != null))
                session.getTransaction().rollback();
            throw ex;
        }
        return results==null? Collections.<T>emptyList():results;
    }
}
