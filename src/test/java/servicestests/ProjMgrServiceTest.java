package servicestests;

import data.request.parameters.DeleteProjMgrParameter;
import data.response.results.GetProjMgrListResult;
import junit.framework.TestCase;
import org.junit.Test;
import services.ProjMgrService;

import java.util.List;

/**
 * Created by liudongdong on 2015/5/26.
 */
public class ProjMgrServiceTest extends TestCase {
    private ProjMgrService pmService;
    public void setUp()
    {
        pmService=new ProjMgrService();
    }
    @Test
    public void testGetProjMgrList()
    {
        List<GetProjMgrListResult>results= pmService.getProjMgrList();
        String s="";
    }

    public void testDeleteProjMgr()
    {
        DeleteProjMgrParameter pa=new DeleteProjMgrParameter();
        pa.setProjID(1);
        pa.setUid(3);
        ProjMgrService pms=new ProjMgrService();
        pms.deleteProjMgr(pa);

        pa.setUid(1);
        pms.deleteProjMgr(pa);
    }
}
