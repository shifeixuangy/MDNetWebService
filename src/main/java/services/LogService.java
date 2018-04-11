package services;

import data.common.TbLog;
import data.common.TbProjInfo;
import data.common.TbUser;
import data.request.parameters.GetLogListParameter;
import data.response.results.GetLogListResult;
import data.response.results.LogResult;
import dataDAO.CommonQueryHelper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2015/5/10.
 */
public class LogService {
    public GetLogListResult getLogList(GetLogListParameter pa) {
        GetLogListResult result = new GetLogListResult();
        result.setLogList(new LinkedList<LogResult>());
        result.setPage(pa.getPage());
        int count = CommonQueryHelper.getLogCount(pa.getUid(), pa.getProjID(), pa.getStartTime(), pa.getEndTime(),pa.getLogType());
        int totalPage = (int) ((count - 1) / pa.getPageSize()) + 1;
        result.setTotalPage(totalPage);
        List<TbLog> logList = CommonQueryHelper.getLogList(pa);
        List<Integer> uids = logList.stream().filter(log->log.getUid()!=null). map(log -> log.getUid()).distinct().collect(Collectors.toList());
        List<Integer> projIDs = logList.stream().filter(log->log.getProjId()!=null).map(log -> log.getProjId()).distinct().collect(Collectors.toList());
        Map<Integer, String> users = new HashMap<>();
        Map<Integer, String> projs = new HashMap<>();
        List<TbUser> tbUsers = CommonQueryHelper.getUser(uids);
        if (tbUsers != null)
            tbUsers.forEach(u -> {
                users.put(u.getId(), u.getName());
            });
        List<TbProjInfo> tbProjInfos = CommonQueryHelper.getProjInfo(projIDs);
        if (tbProjInfos != null)
            tbProjInfos.forEach(pi -> {
                projs.put(pi.getId(), pi.getName());
            });
        if (logList != null)
            logList.forEach(log -> {
                LogResult lr = new LogResult(log);
                lr.setUserName(users.get(log.getUid()));
                lr.setProjName(projs.get(log.getProjId()));
                result.getLogList().add(lr);
            });
        return result;
    }
}
