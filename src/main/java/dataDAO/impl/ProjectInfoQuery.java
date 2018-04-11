package dataDAO.impl;
import commonUtil.CommonVariable;
import commonUtil.StringUtil;
import data.common.TbProjInfo;
import dataDAO.interfaces.CommonQuery;
import dataDAO.interfaces.ObjectsQuery;
import commonUtil.DBUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by liudongdong on 2015/4/19.
 */
public class ProjectInfoQuery extends ObjectsQueryBase<TbProjInfo> {
    @Override
    public String getFrom() {
        return "from data.common.TbProjInfo  ";
    }
}
