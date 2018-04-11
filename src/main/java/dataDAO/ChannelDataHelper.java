package dataDAO;

import commonUtil.CommonVariable;
import commonUtil.StringUtil;
import data.proj.TbChannelData;

/**
 * Created by liudongdong on 2015/12/15.
 */
public class ChannelDataHelper {
    public static void addChannelData(TbChannelData cd,int projID)
    {
        if(StringUtil.isNullOrEmpty(cd.getData())) {
            cd.setData("[]");
            cd.setDataCount(0);
        }
        if(cd.getRecordLength()==null)
            cd.setRecordLength(0f);
        String timeStr= CommonVariable.getDefaultDateFormat().format(cd.getTime());
        StringBuilder sql=new StringBuilder();
        sql.append("INSERT INTO `tb_channel_data`(`RecordLength`,`SensorID`,`ChannelID`,`FileID`,`Time`,`DataCount`,`Data`) ");
        sql.append(" values("+cd.getRecordLength()+","+Integer.toString(cd.getSensorId())+","+Integer.toString(cd.getChannelID())+",'"+cd.getFileID()+"',");
        sql.append("'"+timeStr+"',"+cd.getDataCount().toString()+",'"+cd.getData()+"') ");
        sql.append(" on duplicate key update DataCount="+cd.getDataCount().toString()+",`Data`='"+cd.getData()+"'");
        JDBCExecuteSql.executeUpdate(projID,sql.toString());
    }
}
