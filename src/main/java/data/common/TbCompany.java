package data.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * Created by liudongdong on 2016/3/15.
 */
@Entity
@Table(name = "tb_company", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbCompany {
    @JsonProperty("ID")
    private int id;
    private String companyName;

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
    @Column(name = "CompanyName")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbCompany tbCompany = (TbCompany) o;

        if (id != tbCompany.id) return false;
        return companyName.equals(tbCompany.companyName);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + companyName.hashCode();
        return result;
    }
}
