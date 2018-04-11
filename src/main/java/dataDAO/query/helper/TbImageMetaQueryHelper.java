package dataDAO.query.helper;

import commonUtil.CommonVariable;
import data.proj.TbImageMeta;
import dataDAO.cache.ProjCache;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2016/1/14.
 */
public class TbImageMetaQueryHelper {
    public static List<TbImageMeta>getImages(int projID,int sensorID,boolean isProjImage)
    {
        List<TbImageMeta>images=ProjCache.getProjCache(projID).getImages(isProjImage);
        if(
                (!isProjImage)
                &&
                (sensorID!= CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
                )
            images=images.stream().filter(im->im.getSensorID()!=null&&im.getSensorID().intValue()==sensorID).collect(Collectors.toList());
        return images;
    }
}
