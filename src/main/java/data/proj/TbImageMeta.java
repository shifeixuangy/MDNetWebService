package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import commonUtil.PathUtil;
import data.response.results.ImageWrapper;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liudongdong on 2016/1/14.
 */
@Entity
@Table(name = "tb_image_meta", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbImageMeta {
    @JsonProperty("ID")
    private int id;
    private Integer projID;
    private Integer sensorID;
    private String imagePath;
    private String note;
    private boolean isProjImage;
    private Timestamp uploadTime;

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
    @Column(name = "ProjID")
    public Integer getProjID() {
        return projID;
    }

    public void setProjID(Integer projID) {
        this.projID = projID;
    }
    @Basic
    @Column(name = "SensorID")
    public Integer getSensorID() {
        return sensorID;
    }

    public void setSensorID(Integer sensorID) {
        this.sensorID = sensorID;
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
    @Column(name = "Note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    @Basic
    @Column(name = "IsProjImage")
    public boolean getIsProjImage() {
        return isProjImage;
    }

    public void setIsProjImage(boolean projImage) {
        isProjImage = projImage;
    }

    @Basic
    @Column(name = "UploadTime")
    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    public ImageWrapper toImageWrapper()
    {
        ImageWrapper iw=new ImageWrapper();
        iw.setImageID(this.id);
        iw.setImageNote(this.note);
        iw.setImagePath(PathUtil.getPath(this.imagePath));
        return iw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbImageMeta that = (TbImageMeta) o;

        if (id != that.id) return false;
        if (isProjImage != that.isProjImage) return false;
        if (!projID.equals(that.projID)) return false;
        if (sensorID != null ? !sensorID.equals(that.sensorID) : that.sensorID != null) return false;
        if (!imagePath.equals(that.imagePath)) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        return uploadTime.equals(that.uploadTime);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + projID.hashCode();
        result = 31 * result + (sensorID != null ? sensorID.hashCode() : 0);
        result = 31 * result + imagePath.hashCode();
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (isProjImage ? 1 : 0);
        result = 31 * result + uploadTime.hashCode();
        return result;
    }
}
