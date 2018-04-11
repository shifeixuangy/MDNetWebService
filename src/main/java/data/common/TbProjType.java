package data.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * Created by admin on 2016/6/12.
 */
@Entity
@Table(name = "tb_proj_type", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbProjType {
    @JsonProperty("ID")
    private int id;
    private String name;
    private String alias;
    @JsonProperty("IsNameUpdateable")
    private boolean isNameUpdateable;

    @JsonProperty("IsAliasUpdateable")
    private boolean isAliasUpdateable;

    @JsonProperty("IsDeleteable")
    private boolean isDeleteable;

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
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Alias")
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Basic
    @Column(name = "IsNameUpdateable")
    @JsonIgnore
    public boolean isNameUpdateable() {
        return isNameUpdateable;
    }

    public void setNameUpdateable(boolean nameUpdateable) {
        isNameUpdateable = nameUpdateable;
    }

    @Basic
    @Column(name = "IsAliasUpdateable")
    @JsonIgnore
    public boolean isAliasUpdateable() {
        return isAliasUpdateable;
    }

    public void setAliasUpdateable(boolean aliasUpdateable) {
        isAliasUpdateable = aliasUpdateable;
    }

    @Basic
    @Column(name = "IsDeleteable")
    @JsonIgnore
    public boolean isDeleteable() {
        return isDeleteable;
    }

    public void setDeleteable(boolean deleteable) {
        isDeleteable = deleteable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbProjType that = (TbProjType) o;

        if (id != that.id) return false;
        if (isNameUpdateable != that.isNameUpdateable) return false;
        if (isAliasUpdateable != that.isAliasUpdateable) return false;
        if (isDeleteable != that.isDeleteable) return false;
        if (!name.equals(that.name)) return false;
        return alias.equals(that.alias);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + alias.hashCode();
        result = 31 * result + (isNameUpdateable ? 1 : 0);
        result = 31 * result + (isAliasUpdateable ? 1 : 0);
        result = 31 * result + (isDeleteable ? 1 : 0);
        return result;
    }
}
