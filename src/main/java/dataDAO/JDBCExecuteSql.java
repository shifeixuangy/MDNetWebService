package dataDAO;

import commonUtil.DBUtil;
import commonUtil.JDBCUtil;
import data.proj.TbChannelData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liudongdong on 2015/12/24.
 */
public class JDBCExecuteSql {
    private static Log logger= LogFactory.getLog(JDBCExecuteSql.class);
    public static void executeUpdate(int projID, String sql) {
        Connection conn= JDBCUtil.getJDBCConnection(projID);
        try
        {
            Statement stmt=conn.createStatement();
            try
            {
                stmt.executeUpdate(sql);
            }
            catch (Exception stmtException)
            {
                throw  stmtException;
            }
            finally {
                if(stmt!=null)
                    stmt.close();
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
        finally {
            if(conn!=null)
            {
                try {
                    conn.close();
                }catch (Exception ex)
                {
                    logger.error(ex.getMessage(),ex);
                }
            }
        }
    }

    public static List<TbChannelData> getChannelData(int projID, String sql)
    {
        Connection conn= JDBCUtil.getJDBCConnection(projID);
        try
        {
            Statement stmt=conn.createStatement();
            try
            {
                List<TbChannelData>resultList=new LinkedList<>();
                ResultSet result= stmt.executeQuery(sql);
                while (result.next())
                {
                    TbChannelData tcd=new TbChannelData();
                    tcd.setId(result.getInt("ID"));
                    tcd.setSensorId(result.getInt("SensorID"));
                    tcd.setChannelID(result.getInt("ChannelID"));
                    tcd.setFileID(result.getString("FileID"));
                    tcd.setTime(result.getTimestamp("Time"));
                    tcd.setRecordLength(result.getFloat("RecordLength"));
                    tcd.setDataCount(result.getInt("DataCount"));
                    tcd.setData(result.getString("Data"));
                    resultList.add(tcd);
                }
                result.close();
                return resultList;
            }
            catch (Exception stmtException)
            {
                throw  stmtException;
            }
            finally {
                if(stmt!=null)
                    stmt.close();
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
        finally {
            if(conn!=null)
            {
                try {
                    conn.close();
                }catch (Exception ex)
                {
                    logger.error(ex.getMessage(),ex);
                }
            }
        }
    }
}
