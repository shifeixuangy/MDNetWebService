package dataDAO.proj.interfaces;

import data.proj.TbWarnLog;

import java.util.List;

/**
 * Created by liudongdong on 2015/5/5.
 */
public interface NewWarnLogQuery {
    public List<TbWarnLog>getLog(int projID,List<Integer>sids);
}
