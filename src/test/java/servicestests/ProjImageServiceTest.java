package servicestests;

import data.request.parameters.GetProjImageParameter;
import data.response.results.ProjImageWrapper;
import junit.framework.TestCase;
import org.junit.Test;
import services.ProjImageService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by liudongdong on 2016/1/18.
 */
public class ProjImageServiceTest extends TestCase {
    @Test
    public void testGet()
    {
        GetProjImageParameter pa=new GetProjImageParameter();
        pa.setProjID(-1);
        pa.setImageNumber(1);
        ProjImageService pis=new ProjImageService();
        List<ProjImageWrapper>result= pis.getProjImage(pa);
        String s="";
//        LinkedList
    }
}
