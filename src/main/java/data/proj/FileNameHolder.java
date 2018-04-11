package data.proj;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/12/15.
 */
@Entity
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class FileNameHolder {
    private int id;
    private String fileID;
    private Timestamp time;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name="FileID")
    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    @Basic
    @Column(name="Time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

}
