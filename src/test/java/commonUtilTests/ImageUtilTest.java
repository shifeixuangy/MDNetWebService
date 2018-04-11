package commonUtilTests;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import commonUtil.ImageUtil;
import data.request.parameters.AddCheckImageParameter;
import data.request.parameters.AddCheckVideoParameter;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by liudongdong on 2015/11/4.
 */
public class ImageUtilTest extends TestCase {
    public ImageUtilTest()
    {
        ImageUtil.setRootPath("G:\\MedoService\\target\\MedoService\\");
    }
    @Test
    public void testAddImage()
    {
        byte[]bs=new byte[]{1,2,3,4,5,6};
        String imgContent= Base64.encode(bs);
        AddCheckImageParameter pa=new AddCheckImageParameter();
        pa.setProjID(1);
        pa.setCheckID(1);
        pa.setRecordID(1);
        pa.setImageName("test.jpg");
        pa.setImageContent(imgContent);
        ImageUtil.addImage(pa);
    }

    @Test
    public void testAddVideo()
    {
        byte[]bs=new byte[]{1,2,3,4,5,6};
        String videoContent= Base64.encode(bs);
        AddCheckVideoParameter pa=new AddCheckVideoParameter();
        pa.setProjID(1);
        pa.setCheckID(1);
        pa.setRecordID(1);
        pa.setVideoName("test.mp4");
        pa.setVideoContent(videoContent);
        String fileName= ImageUtil.addVideo(pa);
        ImageUtil.deleteFile(fileName);
    }

}
