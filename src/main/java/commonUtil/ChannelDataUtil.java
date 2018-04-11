package commonUtil;

import data.proj.ChannelDataKey;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by liudongdong on 2015/12/6.
 */
public class ChannelDataUtil {
    private static ChannelDataUtil cdUtil=new ChannelDataUtil();

    public static boolean isDataRepeate(ChannelDataKey dataKey)
    {
        return cdUtil.isDataRepeat(dataKey);
    }


    private List<ChannelDataKey> dataKeys=new LinkedList<>();

    public synchronized boolean isDataRepeat(ChannelDataKey dataKey)
    {
        if(dataKeys.contains(dataKey))
            return true;
        else
        {
            Optional<ChannelDataKey>dk=dataKeys.stream().filter(cd->cd.getProjID()==dataKey.getProjID()&&cd.getSensorID()==dataKey.getSensorID()
            &&cd.getChannelID()==dataKey.getChannelID()).findFirst();
            if(dk.isPresent())
                dataKeys.remove(dk.get());
            dataKeys.add(dataKey);
            return false;
        }
    }
}
