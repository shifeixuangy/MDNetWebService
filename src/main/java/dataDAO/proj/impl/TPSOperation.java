package dataDAO.proj.impl;

import commonUtil.TimeUtil;
import data.proj.TbTpsData;
import dataDAO.ExecuteSql;
import dataDAO.JDBCExecuteSql;

/**
 * Created by liudongdong on 2015/12/20.
 */
public class TPSOperation {
   public void addTPSData(int projID,TbTpsData data,boolean isAvg)
   {
       String tableName=isAvg?"tb_tps_data_avg":"tb_tps_data";
       StringBuilder sql=new StringBuilder();
       sql.append("replace into "+tableName+" (`SensorID`,`Time`,`Disp_X`,`Disp_Y`,`Disp_H`,`Velocity_X`,`Velocity_Y`,`Velocity_H`,`Azimuth`)");
       sql.append("values("+data.getSensorId()+",'"+ TimeUtil.format(data.getTime())+"',"+data.getDispX()+","+data.getDispY()
               +","+data.getDispH()+","+data.getVelocityX()+","+data.getVelocityY()+","+data.getVelocityH()+","+data.getAzimuth()+") ");
       JDBCExecuteSql.executeUpdate(projID,sql.toString());
   }
}
