package servicestests;

import data.request.parameters.GetProjWarnCountAndLevelParameter;
import data.response.results.ProjWarnCountAndLevelResult;
import junit.framework.TestCase;
import org.junit.Test;
import services.WarnLogService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by admin on 2016/5/3.
 */
public class WarnLogServiceTest extends TestCase {
    @Test
    public void testGetProj()
    {
        WarnLogService wls=new WarnLogService();
        GetProjWarnCountAndLevelParameter pa=new GetProjWarnCountAndLevelParameter();
        pa.setProjID(1);
        pa.setBegin(Timestamp.valueOf("2015-01-01 00:00:00"));
        pa.setEnd(Timestamp.valueOf(LocalDateTime.now()));
        List<ProjWarnCountAndLevelResult> resultList=wls.getAllProjWarnCountAndLevel(pa);
        String str="";
    }


}
