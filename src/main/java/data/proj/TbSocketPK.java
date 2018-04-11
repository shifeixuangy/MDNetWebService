package data.proj;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by liudongdong on 2015/4/18.
 */
public class TbSocketPK implements Serializable {
    private int id;

    @Column(name = "ID")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private short model;

    @Column(name = "Model")
    @Id
    public short getModel() {
        return model;
    }

    public void setModel(short model) {
        this.model = model;
    }

    private String addr;

    @Column(name = "Addr")
    @Id
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    private int port;

    @Column(name = "Port")
    @Id
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbSocketPK that = (TbSocketPK) o;

        if (id != that.id) return false;
        if (model != that.model) return false;
        if (port != that.port) return false;
        if (addr != null ? !addr.equals(that.addr) : that.addr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) model;
        result = 31 * result + (addr != null ? addr.hashCode() : 0);
        result = 31 * result + port;
        return result;
    }
}
