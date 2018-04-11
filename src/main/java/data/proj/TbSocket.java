package data.proj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * Created by liudongdong on 2015/4/18.
 */
@Entity
@Table(name = "tb_socket", schema = "")
@IdClass(TbSocketPK.class)
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbSocket {
    @JsonProperty("ID")
    private int id;
    private Short type;
    private short model;
    private String addr;
    private int port;
    private String remark;

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
    @Column(name = "Type")
    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    @Id
    @Column(name = "Model")
    public short getModel() {
        return model;
    }

    public void setModel(short model) {
        this.model = model;
    }

    @Id
    @Column(name = "Addr")
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Id
    @Column(name = "Port")
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Basic
    @Column(name = "Remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbSocket tbSocket = (TbSocket) o;

        if (id != tbSocket.id) return false;
        if (model != tbSocket.model) return false;
        if (port != tbSocket.port) return false;
        if (addr != null ? !addr.equals(tbSocket.addr) : tbSocket.addr != null) return false;
        if (remark != null ? !remark.equals(tbSocket.remark) : tbSocket.remark != null) return false;
        if (type != null ? !type.equals(tbSocket.type) : tbSocket.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (int) model;
        result = 31 * result + (addr != null ? addr.hashCode() : 0);
        result = 31 * result + port;
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
