package commonUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by liudongdong on 2015/4/18.
 */
public class ConnectionStringExtract {
    private static final String SERVERFORMAT="server=";
    private  static final String DBNAMEFORMAT="database=";
    private static final String UIDFORMAT="user id=";
    private static final String PWDFORMAT="password=";
    private static final Log logger= LogFactory.getLog(ConnectionStringExtract.class);
    private String connString;
    private String server;
    private String dbName;
    private String uid;
    private String pwd;


    public ConnectionStringExtract(String connString)
    {
        this.connString=connString;
        String[]strs=connString.split(";");
        for(String s:strs)
        {
            if(s.contains(SERVERFORMAT))
            {
                this.server=s.replace(SERVERFORMAT,"");
            }
            if(s.contains(DBNAMEFORMAT))
            {
                this.dbName=s.replace(DBNAMEFORMAT,"");
            }
            if(s.contains(UIDFORMAT))
            {
                this.uid=s.replace(UIDFORMAT,"");
            }
            if(s.contains(PWDFORMAT))
            {
                this.pwd=s.replace(PWDFORMAT,"");
            }
        }
    }


    /**
     *
     * @return 返回连接传中的服务器地址(不一定包含端口号)
     */
    public String getServer() {
        return server;
    }

    public String getDbName() {
        return dbName;
    }

    public String getUid() {
        return uid;
    }

    public String getPwd() {
        return pwd;
    }

    public String getMySqlConnUrl()
    {
        //jdbc:mysql://localhost:3306/mdm_common_db
        String serverAndPort=getServer();
        //服务器地址中，没有:即服务器地址中不包含端口号
        if(!serverAndPort.contains(":"))
        {
            serverAndPort+=":3306";
        }
        return "jdbc:mysql://"+serverAndPort+"/"+this.getDbName()+"?useUnicode=true&characterEncoding=UTF-8";
    }
}
