package data.proj;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * Created by liudongdong on 2015/4/18.
 */
@Entity
@Table(name = "db_info", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class DBInfo {
    private String dbVersion;

    @Id
    @Column(name = "DB_Version")
    public String getDBVersion() {
        return dbVersion;
    }

    public void setDBVersion(String dbVersion) {
        this.dbVersion=dbVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DBInfo db_info = (DBInfo) o;

        return dbVersion.equals(db_info.dbVersion);

    }

    @Override
    public int hashCode() {
        return dbVersion.hashCode();
    }
}
