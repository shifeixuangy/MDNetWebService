package data.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Created by liudongdong on 2016/2/25.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class DBUpdateInfo {
    private String from;
    private String to;
    private String script;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
