package commonUtil;

/**
 * Created by liudongdong on 2015/8/18.
 */
public class MySqlConnStringExtract {
    //<property name="connection.url">jdbc:mysql://localhost:3306/mdm_common_db?useUnicode=true&amp;characterEncoding=UTF-8</property>
    private String conn;
    private String address;
    private String dbName;
    private String uid;
    private String pwd;
    public MySqlConnStringExtract(String conn)
    {
        this.conn=conn;
    }
}
