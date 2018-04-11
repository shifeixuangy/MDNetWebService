package dataDAO.impl;

import commonUtil.DBUtil;
import commonUtil.StringUtil;
import dataDAO.interfaces.CommonQuery;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

/**
 * Created by liudongdong on 2015/4/25.
 */
public class CommonQueryImpl<T> implements CommonQuery<T> {
    private boolean isNeedPage = false;
    private int pageIndex;
    private int pageSize;

    public CommonQueryImpl() {
    }

    public CommonQueryImpl(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex >= 1 ? pageIndex : 1;
        this.pageSize = pageSize > 0 ? pageSize : 5;
        this.isNeedPage = true;
    }

    @Override
    public List<T> query(String from, String where, int projID) {
        String hql = from;
        if (!StringUtil.isNullOrEmpty(where))
            hql += "   where " + where;
        Session session = null;
        try {
            session = DBUtil.getSession(projID);
            session.beginTransaction();
            List<T> results;
            if (!this.isNeedPage)
                results = session.createQuery(hql).list();
            else
                results = session.createQuery(hql).setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).list();
            session.getTransaction().commit();
            return results==null? Collections.<T>emptyList():results;
        } catch (Exception ex) {
            if ((session != null) && (session.getTransaction() != null))
                session.getTransaction().rollback();
            throw ex;
        }
    }

}
