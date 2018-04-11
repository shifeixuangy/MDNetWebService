package services;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import commonUtil.StringUtil;
import commonUtil.TimeUtil;
import data.common.SensorType;
import data.proj.*;
import data.request.parameters.*;
import data.response.results.GetChannelFileListResult;
import dataDAO.*;
import dataDAO.proj.impl.FileNameQuery;
import dataDAO.proj.impl.TbChannelDataQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2015/12/6.
 */
public class ChannelService {
    private static Log logger= LogFactory.getLog(ChannelService.class);
    public Integer addChannel(AddAndUpdateChannelParameter pa) {
        return DataHelper.add(pa.getChannel(), pa.getProjID());
    }

    public void updateChannel(AddAndUpdateChannelParameter pa) {
        DataHelper.update(pa.getChannel(), pa.getProjID());
    }

    public void deleteChannel(DeleteChannelParameter pa) {
        if (StringUtil.isNullOrEmpty(pa.getChannelName()))
            return;
        List<TbChannel> chs = ChannelQueryHelper.getChannels(pa.getProjID(), pa.getSensorID());
        if (CollectionUtil.isNullOrEmpty(chs))
            return;
        Optional<TbChannel> channelOptional = chs.stream().filter(t -> pa.getChannelName().equals(t.getChannelName())).findFirst();
        if (channelOptional.isPresent())
            DataHelper.delete(channelOptional.get(), pa.getProjID());
    }

    public List<TbChannel> getChannelList(GetChannelListParameter pa) {
        return ChannelQueryHelper.getChannels(pa.getProjID(), pa.getSensorID(), pa.getChannelID());
    }

    public Integer addChannelData(AddChannelDataParameter pa) {
        try {
            return DataHelper.add(pa.getChannelData(), pa.getProjID());
        } catch (ConstraintViolationException ex) {
            return -1;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Integer addData(AddDataParameter pa) {
        if (pa.getSensor() == null)
            return -1;
        final TbSensorAttri attri = checkAndGetShockAttri(pa.getProjID(), pa.getSensor());
        if (CollectionUtil.isNullOrEmpty(pa.getChannelDatas()))
            return -1;
        ShockExValues shockExValues=getShockExValues(attri.getExValues());
        pa.getChannelDatas().forEach(cdw -> {
            TbChannel ch = cdw.getChannel();
            ch = checkAndGetChannel(pa.getProjID(), attri.getId(), ch);
            if ((!StringUtil.isNullOrEmpty(cdw.getChannelData()))
                    && (!StringUtil.isNullOrEmpty(cdw.getFileID()))
                    && (cdw.getTime() != null)) {
                TbChannelData cd = new TbChannelData();
                cd.setSensorId(attri.getId());
                cd.setChannelID(ch.getId());
                cd.setData(cdw.getChannelData());
                cd.setTime(cdw.getTime());
                cd.setFileID(cdw.getFileID());
                cd.setRecordLength(shockExValues.getRecordLength());
                try {
                    double[] data = CommonVariable.getObjectMapper().readValue(cdw.getChannelData(), double[].class);
                    cd.setDataCount(data.length);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                ChannelDataHelper.addChannelData(cd, pa.getProjID());
            }
        });
        return 1;
    }

    public List<TbChannelData> getChannelDataList(GetChannelDataListParameter pa) {
        StringBuilder where = new StringBuilder();
        where.append("select * from tb_channel_data where ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        where.append(" SensorID=" + Integer.toString(pa.getSensorID()) + "   ");
        if (pa.getChannelID() != CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
            where.append(" and ChannelID=" + Integer.toString(pa.getChannelID()) + " ");
        where.append(" and Time between '" + sdf.format(pa.getBeginTime()) + "' and '" + sdf.format(pa.getEndTime()) + "' ");
//        TbChannelDataQuery cdq = new TbChannelDataQuery();
        return JDBCExecuteSql.getChannelData(pa.getProjID(),where.toString());
    }

    private ShockExValues getShockExValues(String exValues)
    {
        ShockExValues shockExValues=new ShockExValues();
        if(StringUtil.isNullOrEmpty(exValues))
            return shockExValues;
        try
        {
            shockExValues= CommonVariable.getObjectMapper().readValue(exValues,ShockExValues.class);
            return shockExValues;
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    private TbSensorAttri checkAndGetShockAttri(int projID, TbSensorAttri attri) {
        List<TbSensorAttri> attris = ProjQueryHelper.getSensorList(projID, SensorType.TYPE_SHOCK.toInt());
        if (CollectionUtil.isNullOrEmpty(attris))
            attris = Collections.emptyList();
        Optional<TbSensorAttri> aiOptional = attris.stream().filter(t -> attri.getName().equals(t.getName())).findFirst();
        if (!aiOptional.isPresent()) {
            Integer id = DataHelper.add(attri, projID);
            logger.info("添加了一个传感器："+id);
            return attri;
        } else {
            TbSensorAttri aiMemory = aiOptional.get();
            attri.setId(aiMemory.getId());
            attri.setAlias(aiMemory.getAlias());
            if (!attri.equals(aiMemory)) {
                DataHelper.update(attri, projID);
                logger.info("修改了一个传感器："+attri.getId());
            }
            return attri;
        }
    }

    private TbChannel checkAndGetChannel(int projID, int sensorID, TbChannel ch) {
        ch.setSensorID(sensorID);
        List<TbChannel> chs = ChannelQueryHelper.getChannels(projID, sensorID);
        if (CollectionUtil.isNullOrEmpty(chs))
            chs = Collections.emptyList();
        Optional<TbChannel> chOptional = chs.stream().filter(c -> c.getSensorID() == sensorID && c.getChannelName().equals(ch.getChannelName())).findFirst();
        //数据库里有通道信息了
        if (chOptional.isPresent()) {
            TbChannel chMemory = chOptional.get();
            ch.setId(chMemory.getId());
            if (!ch.equals(chMemory)) {
                DataHelper.update(ch, projID);
                logger.info("修改了一个通道："+ch.getId());
            }
            return ch;
        } else {
            DataHelper.add(ch, projID);
            logger.info("添加了一个通道："+ch.getId());
            return ch;
        }
    }

    public List<GetChannelFileListResult> getChannelFileList(GetChannelFileListParameter pa) {
        FileNameQuery fq = new FileNameQuery();
        List<FileNameHolder> nameHolders = fq.query(pa.getProjID(), pa.getSensorID(), pa.getBeginTime(), pa.getEndTime());
        if (CollectionUtil.isNullOrEmpty(nameHolders))
            return Collections.emptyList();
        return nameHolders.stream().map(f -> new GetChannelFileListResult(f.getFileID(), f.getTime())).distinct().collect(Collectors.toList());
    }

    public List<TbChannelData> getChannelDataListByFile(GetChannelDataListByFileParameter pa) {
//        TbChannelDataQuery cq = new TbChannelDataQuery();
        StringBuilder where = new StringBuilder();
        where.append("select * from tb_channel_data where ");
        where.append(" FileID='" + pa.getFileID() + "' ");
        if(pa.getTime()!=null)
        {
            where.append(" and Time='"+ TimeUtil.format(pa.getTime())+"' ");
        }
        if (pa.getSensorID() != CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID) {
            where.append(" and SensorID=" + Integer.toString(pa.getSensorID()));
            if (pa.getChannelID() != CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID) {
                where.append(" and ChannelID=" + Integer.toString(pa.getChannelID()));
            }
        }
        return JDBCExecuteSql.getChannelData(pa.getProjID(),where.toString());
    }

    public List<TbChannelData> getChannelDataAccelerListByFile(GetChannelDataListByFileParameter pa) {
        List<TbChannelData>datas=getChannelDataListByFile(pa);
        if(CollectionUtil.isNullOrEmpty(datas))
            return datas;
        List<TbChannelData>result=new LinkedList<>();
        datas.forEach(cd->{
            if((cd.getRecordLength()!=null)&&(!cd.getRecordLength().equals(0))
                    &&(cd.getDataCount()!=null)&&(cd.getDataCount()>0))
            {
                String dataStr=cd.getData();
                try
                {
                    double[]dd=CommonVariable.getObjectMapper().readValue(dataStr,double[].class);
                    double []ddVelocity=new double[dd.length];
                    double pre=0;
                    double interval=cd.getRecordLength()/cd.getDataCount();
                    for(int i=0;i<dd.length;++i)
                    {
                        ddVelocity[i]=(dd[i]-pre)/interval;
                        pre=dd[i];
                    }
                    String velocityStr=CommonVariable.getObjectMapper().writeValueAsString(ddVelocity);
                    cd.setData(velocityStr);
                    result.add(cd);
                }
                catch (Exception ex)
                {throw new RuntimeException(ex);}
            }
        });
        return result;
    }

}
