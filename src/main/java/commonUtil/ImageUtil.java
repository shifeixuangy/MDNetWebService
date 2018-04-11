package commonUtil;

import com.mysql.jdbc.util.Base64Decoder;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import data.request.parameters.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by liudongdong on 2015/11/4.
 */
public class ImageUtil {
    private static String rootPath;
    public static String addImage(AddCheckImageParameter pa)
    {
            if (pa.getProjID() == CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID
                    || StringUtil.isNullOrEmpty(pa.getImageName())
                    || StringUtil.isNullOrEmpty(pa.getImageContent()))
                return null;
            return createFile("images\\"+Integer.toString(pa.getProjID()),pa.getImageName(),pa.getImageContent());
    }

    public static String addImage(AddProjImageParameter pa)
    {
        if (pa.getProjID() == CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID
                || StringUtil.isNullOrEmpty(pa.getImageName())
                || StringUtil.isNullOrEmpty(pa.getImageContent()))
            return null;
        return createFile("images\\"+Integer.toString(pa.getProjID()),pa.getImageName(),pa.getImageContent());
    }

    public static String addImage(AddSensorImageParameter pa)
    {
        if (pa.getProjID() == CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID
                || StringUtil.isNullOrEmpty(pa.getImageName())
                || StringUtil.isNullOrEmpty(pa.getImageContent()))
            return null;
        return createFile("images\\"+Integer.toString(pa.getProjID()),pa.getImageName(),pa.getImageContent());
    }

    public static String addImage(SetUserHeadPhotoParameter pa)
    {
        if(StringUtil.isNullOrEmpty(pa.getPhotoName())
                ||StringUtil.isNullOrEmpty(pa.getPhotoContent()))
            return null;
        return createFile("images\\common",pa.getPhotoName(),pa.getPhotoContent());
    }

    public static String addVideo(AddCheckVideoParameter pa)
    {
        if (pa.getProjID() == CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID
                || StringUtil.isNullOrEmpty(pa.getVideoName())
                || StringUtil.isNullOrEmpty(pa.getVideoContent()))
            return null;
        return createFile("videos\\"+Integer.toString(pa.getProjID()),pa.getVideoName(),pa.getVideoContent());
    }


    public static void deleteFile(String filePath)
    {
        File f=new File(rootPath+filePath);
        f.delete();
    }


    private static String createFile(String directory,String fileName,String fileContent)
    {
        try {
            if (StringUtil.isNullOrEmpty(directory)
                    || StringUtil.isNullOrEmpty(fileName)
                    || StringUtil.isNullOrEmpty(fileContent))
                return null;
            drictoryCreateIfNotExists(directory);
            String uuid = UUID.randomUUID().toString();
            String filePath = directory + "\\" + uuid + fileName;
            File tempFile = new File(rootPath + filePath);
            if (!tempFile.exists())
                tempFile.createNewFile();
            OutputStream os=new FileOutputStream(tempFile);
            byte[]bs= Base64.decode(fileContent);
            os.write(bs);
            os.flush();
            os.close();
            return filePath;
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }


    private static void drictoryCreateIfNotExists(String path)
    {
        File f=new File(rootPath+path);
        if(!f.exists())
            f.mkdir();
    }


    public static String getRootPath() {
        return rootPath;
    }

    public static void setRootPath(String rootPath) {
        ImageUtil.rootPath = rootPath;
    }
}
