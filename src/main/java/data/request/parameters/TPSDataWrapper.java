package data.request.parameters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by liudongdong on 2015/12/15.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TPSDataWrapper {
    @NotNull
    private TPSConfig config;
    private List<TPSData>datas;

    public TPSConfig getConfig() {
        return config;
    }

    public void setConfig(TPSConfig config) {
        this.config = config;
    }

    public List<TPSData> getDatas() {
        return datas;
    }

    public void setDatas(List<TPSData> datas) {
        this.datas = datas;
    }
}
