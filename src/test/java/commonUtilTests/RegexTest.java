package commonUtilTests;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liudongdong on 2015/5/1.
 */
public class RegexTest  extends TestCase{
    private static String s1="\"projid\":(?<pid>\\d+)";
    private static String s2="ProjID";
    private static String s3="el";
    private static Pattern PROJ_PATTERN=Pattern.compile(s1,Pattern.CASE_INSENSITIVE);

    @Test
    public void testGroup()
    {
        String str1="{\"ProjID\":3}";
        String str2="{\"ProjID\":500,\"test\":\"hello\"}";
        String str3="Hello";
        Matcher m3=PROJ_PATTERN.matcher(str3);
        boolean b3=m3.find();
        Matcher m1=PROJ_PATTERN.matcher(str1);
//        boolean b1=m1.matches();
        if(m1.find())
        {
            String spid=m1.group("pid");
            String t="";
        }

        Matcher m2=PROJ_PATTERN.matcher(str2);
        if(m2.find())
        {
            String spid=m2.group("pid");
            String t3="";
        }

    }

}
