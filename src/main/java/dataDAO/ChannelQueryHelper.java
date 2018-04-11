package dataDAO;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import data.proj.TbChannel;
import dataDAO.cache.ProjCache;
import dataDAO.proj.impl.TbChannelQuery;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2015/12/4.
 */
public class ChannelQueryHelper {
    public static List<TbChannel>getChannels(int projID,int sensorID)
    {
        List<TbChannel>chs=getChannels(projID);
        if(sensorID!=CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            return chs.stream().filter(t->t.getSensorID()==sensorID).collect(Collectors.toList());
        return chs;
    }
    public static List<TbChannel>getChannels(int projID,int sensorID,int channelID)
    {
       List<TbChannel>chs=getChannels(projID, sensorID);
        if(CollectionUtil.isNullOrEmpty(chs))
            return chs;
        if(channelID==CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            return chs;
        return chs.stream().filter(t->t.getId()==channelID).collect(Collectors.toList());
    }

    public static List<TbChannel>getChannels(int projID)
    {
        ProjCache pi=ProjCache.getProjCache(projID);
        if(pi==null)
            return Collections.emptyList();
        return pi.getChannels();
    }
}
