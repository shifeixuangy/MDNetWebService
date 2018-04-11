package servicestests;

import commonUtil.CommonVariable;
import data.common.AccessType;
import data.response.results.LevelProjWrapper;
import junit.framework.TestCase;
import org.junit.Test;
import services.CommonService;
import services.LevelService;

import java.util.List;

/**
 * Created by liudongdong on 2016/3/22.
 */
public class LevelServiceTest extends TestCase {
    @Test
    public void testDeleteLevel()
    {
        LevelService ls=new LevelService();
        ls.deleteLevel(1);
    }

    @Test
    public void testGetLevelServiceProj()
    {
        CommonService cs=new CommonService();
        String accessToken=cs.signIn("admin","admin", AccessType.WEB);

        LevelService ls=new LevelService();
        List<LevelProjWrapper>result= ls.getLevelProjList(accessToken);
        try {
            String strResult = CommonVariable.getObjectMapper().writeValueAsString(result);
            int i=strResult.length();
        }
        catch (Exception ex)
        {}
        String sstr="";
    }
}
