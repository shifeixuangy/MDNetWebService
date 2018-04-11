package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * Created by liudongdong on 2015/11/2.
 */
@Entity
@Table(name = "check_image", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class CheckImage {
    @JsonProperty("ID")
    private int id;
    private int checkID;
    private int recordID;
    private String imagePath;
    private String Remark;

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
    @Column(name = "CheckID")
    public int getCheckID() {
        return checkID;
    }

    public void setCheckID(int checkID) {
        this.checkID = checkID;
    }

    @Basic
    @Column(name = "RecordID")
    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    @Basic
    @Column(name = "ImagePath")
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    @Basic
    @Column(name = "Remark")
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckImage)) return false;

        CheckImage that = (CheckImage) o;

        if (checkID != that.checkID) return false;
        if (id != that.id) return false;
        if (recordID != that.recordID) return false;
        if (Remark != null ? !Remark.equals(that.Remark) : that.Remark != null) return false;
        if (!imagePath.equals(that.imagePath)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + checkID;
        result = 31 * result + recordID;
        result = 31 * result + imagePath.hashCode();
        result = 31 * result + (Remark != null ? Remark.hashCode() : 0);
        return result;
    }
}
