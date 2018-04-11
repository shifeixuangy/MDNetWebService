package data.response.results;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2016/1/20.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class CommonInfo {
    private String apiVersion;
    private String projToken;
    private String appName;
    private String background;

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getProjToken() {
        return projToken;
    }

    public void setProjToken(String projToken) {
        this.projToken = projToken;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
