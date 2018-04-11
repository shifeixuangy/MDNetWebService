package commonUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by liudongdong on 2015/8/18.
 */
public class DBConfig {
    private static final Log logger= LogFactory.getLog(DBConfig.class);
    private static DBConfig commonConfig;
    private static String PROJ_DB_NAME;
    private String addr;
    private String dbName;
    private String uid;
    private String pwd;

    static
    {
        try( InputStream inputStream=CommonVariable.class.getResourceAsStream("/dbconfig.properties");)
        {
            Properties commonProperties=new Properties();
            commonProperties.load(inputStream);
            String addr=commonProperties.getProperty("dbaddress");
            String dbname=commonProperties.getProperty("dbname");
            String uid=commonProperties.getProperty("uid");
            String pwd=commonProperties.getProperty("pwd");
            PROJ_DB_NAME=commonProperties.getProperty("proj_db_name");
            commonConfig=new DBConfig(addr,dbname,uid,pwd);
        }
        catch (Exception ex)
        {
            logger.warn("读取commonVariable配置出错",ex);
        }
    }

    public DBConfig(String addr, String dbName, String uid, String pwd) {
        this.addr = addr;
        this.dbName = dbName;
        this.uid = uid;
        this.pwd = pwd;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMySqlString()
    {
        return "jdbc:mysql://"+this.addr+"/"+this.dbName+"?useUnicode=true&characterEncoding=UTF-8";
    }

    public String getDBStorageString()
    {
        return "server="+this.addr+";user id="+this.uid+";password="+this.pwd+";database="+this.dbName;
    }

    public static DBConfig getCommonConfig()
    {
        return commonConfig;
    }
    public static String getPROJ_DB_NAME()
    {
        return PROJ_DB_NAME;
    }
    public static DBConfig valueOf(String connStr)
    {
        ConnectionStringExtract cse=new ConnectionStringExtract(connStr);
        return new DBConfig(cse.getServer(),cse.getDbName(),cse.getUid(),cse.getPwd());
    }
}
