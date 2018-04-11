package dataDAO.proj.interfaces;

import data.common.SensorType;
import data.common.TbRegion;
import data.proj.*;
import data.request.parameters.GetSensorDataRecordParameter;
import data.request.parameters.GetWarnLogListParameter;
import data.response.results.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by liudongdong on 2015/6/8.
 */
public interface IProjQueryHelper {
    public  TbSensorAttri getSensor(int projID, int sid);
    public  List<TbSensorAttri> getSensorList(int projID, int sensorType);


    public  SensorResult getSensorResults(int projID, int sid);
    public  SensorResult getSensorResults(int projID, TbSensorAttri attri) ;

    public  List<SensorResult> getSensorResults(int projID, SensorType sensorType);


    public  List<Object> getSensorDataRecord(GetSensorDataRecordParameter pa);

    public  List<TbWarnValue> getWarnValue(int projID) ;

    public  TbWarnValue getWarnValue(int projID, int sensorType, String warnType, int warnLevel);

    public  List<TbWarnRevise> getWarnRevise(int projID);

    public  TbWarnRevise getWarnRevise(int projID, int sensorType, int sensorID, String warnChildType) ;

    public  List<WarnReviseResult> getWarnReviseResult(int projID) ;


    public  List<WarnLogCountResult> getWarnLogCountList(int projID, Timestamp begin, Timestamp end);


    public  GetWarnLogListResultWarpper getWarnLogList(GetWarnLogListParameter pa);

    public  int getWranLogCount(int projID, int sensorID, Timestamp begin, Timestamp end, int warnLogStatus);

    public  List<TbWarnPerson> getWarnPersonList(int projID) ;

    public  TbWarnPerson getWarnPerson(int projID, String userName);

    /**
     * @param projID
     * @param areaID 等于-1时查询所有
     * @return
     */
    public  List<TbMonitorArea> getMonitorArea(int projID, int areaID);

    public  List<TbGroup> getGroup(int projID, int groupID);

    public  List<TbMonitorType> getMonitorType(int projID) ;

    public  List<TbRegion> getRegion(int projID, int regionID);

    public List<TbChannel>getChannels(int projID);
    public List<TbImageMeta>getImages(int projID);
}
