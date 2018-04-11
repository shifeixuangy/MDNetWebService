package data.proj;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liudongdong on 2015/4/18.
 */
@Entity
@Table(name = "tb_monitor_type", schema = "")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
public class TbMonitorType {
    public static final int DEFAULT_SHOW_INTERVAL   =24;
    @JsonProperty("ID")
    private int id;
    private int sensorType;
    private String typeName;
    private String name;
    private boolean isValid;
    private String values;
    private int displayOrder;
    private int showInterval;

    public TbMonitorType() {
        showInterval=DEFAULT_SHOW_INTERVAL;
    }

    public TbMonitorType(int sensorType, String typeName, String name, boolean isValid, String values,int displayOrder,int showInterval) {
        this.sensorType = sensorType;
        this.typeName = typeName;
        this.name = name;
        this.isValid = isValid;
        this.values = values;
        this.displayOrder=displayOrder;
        this.showInterval=showInterval;
    }

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
    @Column(name = "SensorType")
    public int getSensorType() {
        return sensorType;
    }

    public void setSensorType(int sensorType) {
        this.sensorType = sensorType;
    }

    @Basic
    @Column(name = "TypeName")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
    @Column(name = "IsValid")
    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    @Basic
    @Column(name = "`Values`")
    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    @Basic
    @Column(name = "DisplayOrder")
    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Basic
    @Column(name = "ShowInterval")
    public int getShowInterval() {
        return showInterval;
    }

    public void setShowInterval(int showInterval) {
        this.showInterval = showInterval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbMonitorType that = (TbMonitorType) o;

        if (id != that.id) return false;
        if (sensorType != that.sensorType) return false;
        if (isValid != that.isValid) return false;
        if (displayOrder != that.displayOrder) return false;
        if (showInterval != that.showInterval) return false;
        if (typeName != null ? !typeName.equals(that.typeName) : that.typeName != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return values != null ? values.equals(that.values) : that.values == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + sensorType;
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isValid ? 1 : 0);
        result = 31 * result + (values != null ? values.hashCode() : 0);
        result = 31 * result + displayOrder;
        result = 31 * result + showInterval;
        return result;
    }

    public static List<TbMonitorType> defaultMonitorTypes() {
        List<TbMonitorType> mTypes = new LinkedList<TbMonitorType>();
        mTypes.addAll(Arrays.asList(
                new TbMonitorType(0,"gps","地表位移",true,"位移,沉降,速度,加速度",0,DEFAULT_SHOW_INTERVAL),
                new TbMonitorType(1,"全站仪","全站仪",false,"全站仪",1,DEFAULT_SHOW_INTERVAL),
                new TbMonitorType(2,"位移计","内部位移",true,"位移,速度",2,DEFAULT_SHOW_INTERVAL),
                new TbMonitorType(3,"雨量","雨量",true,"雨量",3,DEFAULT_SHOW_INTERVAL),
                new TbMonitorType(4,"水位","库水位",true,"水位",4,DEFAULT_SHOW_INTERVAL),
                new TbMonitorType(5,"干滩","干滩",true,"滩长,安全超高,坡比",5,DEFAULT_SHOW_INTERVAL),
                new TbMonitorType(6,"温度计","温度",false,"温度",6,DEFAULT_SHOW_INTERVAL),
                new TbMonitorType(7,"浸润线","浸润线",true,"浸润线",7,DEFAULT_SHOW_INTERVAL),
                new TbMonitorType(8,"倾角计","倾角计",false,"倾角计",8,DEFAULT_SHOW_INTERVAL),
                new TbMonitorType(9,"静力水准","静力水准",true,"沉降",9,DEFAULT_SHOW_INTERVAL),
                new TbMonitorType(10,"视频","视频",true,"视频",10,DEFAULT_SHOW_INTERVAL),
                new TbMonitorType(11,"量水堰","量水堰",false,"流量",11,DEFAULT_SHOW_INTERVAL),
                new TbMonitorType(12,"风特性","风特性",true,"风速",12,DEFAULT_SHOW_INTERVAL),
                new TbMonitorType(13,"浊度仪","浊度仪",true,"浊度",13,DEFAULT_SHOW_INTERVAL),
                new TbMonitorType(14,"裂缝计","裂缝",true,"裂缝",14,DEFAULT_SHOW_INTERVAL),
                new TbMonitorType(15,"土壤含水率","土壤含水率",true,"土壤含水率",15,DEFAULT_SHOW_INTERVAL),
                new TbMonitorType(16,"爆破震动","爆破震动",true,"爆破震动",16,DEFAULT_SHOW_INTERVAL),
                new TbMonitorType(17,"多点位移计","多点位移",true,"位移",17,DEFAULT_SHOW_INTERVAL)
        ));
        return mTypes;
    }
}

















