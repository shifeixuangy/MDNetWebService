package data.response.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import data.common.TbLog;

import java.util.List;

/**
 * Created by liudongdong on 2015/5/10.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetLogListResult {
    
    private int page;
    
    private int totalPage;
    
    private List<LogResult>logList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<LogResult> getLogList() {
        return logList;
    }

    public void setLogList(List<LogResult> logList) {
        this.logList = logList;
    }
}
