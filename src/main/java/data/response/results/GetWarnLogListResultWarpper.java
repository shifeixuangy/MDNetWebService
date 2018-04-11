package data.response.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

/**
 * Created by liudongdong on 2015/5/7.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetWarnLogListResultWarpper {
    
    private int page;
    
    private int totalPage;
    
    private List<GetWarnLogListResult>warnLogList;

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

    public List<GetWarnLogListResult> getWarnLogList() {
        return warnLogList;
    }

    public void setWarnLogList(List<GetWarnLogListResult> warnLogList) {
        this.warnLogList = warnLogList;
    }
}
