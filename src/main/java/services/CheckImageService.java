package services;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import commonUtil.ImageUtil;
import commonUtil.PathUtil;
import data.proj.CheckImage;
import data.request.parameters.AddCheckImageParameter;
import data.request.parameters.DeleteCheckImageParameter;
import data.request.parameters.GetCheckImageParameter;
import dataDAO.DataHelper;
import dataDAO.check.CheckQueryHelper;

import java.util.List;

/**
 * Created by liudongdong on 2015/11/4.
 */
public class CheckImageService {
    public List<CheckImage>getImage(GetCheckImageParameter pa)
    {
        List<CheckImage>imgs= CheckQueryHelper.getImage(pa.getProjID(), pa.getCheckID(), pa.getRecordID(), pa.getImageID());
        if(!CollectionUtil.isNullOrEmpty(imgs))
        {
            for(CheckImage ci:imgs)
            {
                ci.setImagePath(PathUtil.getPath(ci.getImagePath()));
            }
        }
        return imgs;
    }

    public Integer addImage(AddCheckImageParameter pa)
    {
        String imgPath= ImageUtil.addImage(pa);
        CheckImage ci=new CheckImage();
        ci.setCheckID(pa.getCheckID());
        ci.setRecordID(pa.getRecordID());
        ci.setImagePath(imgPath);
        return DataHelper.add(ci,pa.getProjID());
    }

    public void deleteImage(DeleteCheckImageParameter pa)
    {
        GetCheckImageParameter getPa=new GetCheckImageParameter();
        getPa.setProjID(pa.getProjID());
        getPa.setImageID(pa.getImageID());
        List<CheckImage> imgs= getImage(getPa);
        if(CollectionUtil.isNullOrEmpty(imgs))
            return;
        DataHelper.delete(imgs.get(0),pa.getProjID());
        String imgPath=imgs.get(0).getImagePath();
        ImageUtil.deleteFile(imgPath);
    }



}
