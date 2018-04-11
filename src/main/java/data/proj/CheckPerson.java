package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * Created by liudongdong on 2015/11/2.
 */
@Entity
@Table(name = "check_person", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class CheckPerson {
    @JsonProperty("ID")
    private int id;
    private int checkID;
    private int userID;
    private String name;

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
    @Column(name = "UserID")
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckPerson)) return false;

        CheckPerson that = (CheckPerson) o;

        if (checkID != that.checkID) return false;
        if (id != that.id) return false;
        if (userID != that.userID) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + checkID;
        result = 31 * result + userID;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
