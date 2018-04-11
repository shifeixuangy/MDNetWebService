package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liudongdong on 2015/4/18.
 */
@Entity
@Table(name = "tb_event_record", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbEventRecord{
    @JsonProperty("ID")
    private int id;
    private Timestamp time;
    private String record;

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
    @Column(name = "`Time`")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "Record",length = 2100000)
    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbEventRecord that = (TbEventRecord) o;

        if (id != that.id) return false;
        if (!time.equals(that.time)) return false;
        return record.equals(that.record);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + time.hashCode();
        result = 31 * result + record.hashCode();
        return result;
    }
}
