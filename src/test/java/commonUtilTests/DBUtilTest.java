package commonUtilTests;

import commonUtil.CommonVariable;
import commonUtil.DBUtil;
import data.common.TbProjInfo;
import dataDAO.impl.ProjectInfoQuery;
import dataDAO.interfaces.ObjectsQuery;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by liudongdong on 2015/4/19.
 */
public class DBUtilTest extends TestCase {
    ObjectsQuery<TbProjInfo>projsQuery=new ProjectInfoQuery();
    private List<TbProjInfo>projs=null;
    @Before
    public void setUp()
    {
        projs=projsQuery.query(null, CommonVariable.DEFAULT_PROJID);
    }
    @Test
    public void testGetSessionFactoryCount()
    {
        int pCount=projs.size();
        Assert.assertTrue((1+pCount)== DBUtil.getSessionFactoryCount());
    }

    @Test
    public void testGetSessionFactory()
    {
        projs.forEach(proj->{
            SessionFactory sf=DBUtil.getSessionFactory(proj.getId());
            Assert.assertTrue(sf!=null);
        });
    }
    @Test
    public void testGetSession()
    {
        projs.forEach(proj->{
            Session session=DBUtil.getSession(proj.getId());
            Assert.assertTrue(session!=null);
        });
    }

    @Test
    public void testTableExists()
    {
        boolean f1= DBUtil.isTableExists(1,"db_info");
        boolean f2=DBUtil.isTableExists(1,"haha");
        assert  f1;
        assert (!f2);
    }

    @Test
    public void testDBVersion()
    {
        String dbVersion=DBUtil.getDBVersion(1);
        assert dbVersion.equals("1.1.0");
    }

    @Test
    public void testLambda()
    {
        Student student=new Student();
        Consumer<String> consumer=student::setName;
        consumer.accept("ldd");

        StringBuilder stringBuilder=new StringBuilder();
        //这种情况通过方法应用可以，但是通过lambda方法体赋值确不行
                                    //该方法的签名为：String->StringBuilder
        Consumer<String>consumer1=stringBuilder::append;
    }

    @Test
    public void testStringSplit()
    {
        String str="s1;s2;s3";
        String []arr=str.split(";");
        int i=arr.length;
    }

    public static class Student
    {
        public Student setName(String str)
        {
            System.out.println(str);
            return this;
        }

        public static Student setAdddr(String str)
        {

            return new Student();
        }

        public static Student setAddr(String str1,String str2)
        {
            return new Student();
        }
    }

}
