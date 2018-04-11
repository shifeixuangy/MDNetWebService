package data.response.results;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/12/15.
 */
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class GetChannelFileListResult {
    private String fileID;
    private Timestamp time;

    public GetChannelFileListResult() {
    }

    public GetChannelFileListResult(String fileID, Timestamp time) {
        this.fileID = fileID;
        this.time = time;
    }

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GetChannelFileListResult that = (GetChannelFileListResult) o;

        if (!fileID.equals(that.fileID)) return false;
        return time.equals(that.time);

    }

    @Override
    public int hashCode() {
        int result = fileID.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }
}
