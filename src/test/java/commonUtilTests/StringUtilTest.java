package commonUtilTests;

import commonUtil.CommonVariable;
import commonUtil.StringUtil;
import commonUtil.TimeUtil;
import init.config.LogCleanerInit;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by liudongdong on 2015/4/23.
 */
public class StringUtilTest extends TestCase {
    @Test
    public void testIsNullOrEmpty()
    {
        String s=null;
        String s1="";
        String s2="h";
        Assert.assertTrue(StringUtil.isNullOrEmpty(s));
        Assert.assertTrue(StringUtil.isNullOrEmpty(s1));
        Assert.assertFalse(StringUtil.isNullOrEmpty(s2));
    }

    @Test
    public void testTimeUtil()
    {
        List<Timestamp>result= TimeUtil.getTimePreDayBeginEnd(Timestamp.valueOf(LocalDateTime.now()));
        String str="";
    }

    @Test
    public void testClear()
    {
        CommonVariable.setRootPath("F:\\java\\javaproject\\MedoService\\target\\MedoService\\");
        LogCleanerInit ci=new LogCleanerInit();
        ci.clear();
    }

    @Test
    public void testJson()
    {
        Student stu=new Student(1,"liu\"dongdong");
        try {
            String stuStr = CommonVariable.getObjectMapper().writeValueAsString(stu);
            String str="";
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    public static class Student
    {
        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
