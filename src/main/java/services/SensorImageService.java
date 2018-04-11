package services;

import commonUtil.*;
import data.common.SensorType;
import data.proj.TbImageMeta;
import data.request.parameters.AddSensorImageParameter;
import data.request.parameters.DeleteSensorImageParameter;
import data.request.parameters.GetSensorImageParameter;
import data.response.results.ImageWrapper;
import data.response.results.ProjImageWrapper;
import data.response.results.SensorImageWrapper;
import dataDAO.CommonQueryHelper;
import dataDAO.DataHelper;
import dataDAO.ProjQueryHelper;
import dataDAO.query.helper.TbImageMetaQueryHelper;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2016/1/14.
 */
public class SensorImageService {
    public int addSensorImage(AddSensorImageParameter pa) {
        String imagePath = ImageUtil.addImage(pa);
        if (StringUtil.isNullOrEmpty(imagePath))
            return -1;
        TbImageMeta im = new TbImageMeta();
        im.setImagePath(imagePath);
        im.setIsProjImage(false);
        im.setNote(pa.getImageNote());
        im.setProjID(pa.getProjID());
        im.setSensorID(pa.getSensorID());
        im.setUploadTime(new Timestamp(System.currentTimeMillis()));
        return DataHelper.add(im, pa.getProjID());
    }

    public void deleteSensorImage(DeleteSensorImageParameter pa) {
        TbImageMeta im = new TbImageMeta();
        im.setId(pa.getImageID());
        DataHelper.delete(im, pa.getProjID());
    }

    public List<SensorImageWrapper> getSensorImage(GetSensorImageParameter pa) {
        List<Integer> sensorIDs;
        if (pa.getSensorID() != CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID) {
            sensorIDs = Arrays.asList(pa.getSensorID());
        } else {
            sensorIDs = ProjQueryHelper.getSensorList(pa.getProjID(), SensorType.All.toInt()).stream().map(p -> p.getId()).collect(Collectors.toList());
        }
        if (CollectionUtil.isNullOrEmpty(sensorIDs))
            return Collections.emptyList();
        List<SensorImageWrapper> result = new LinkedList<>();
        sensorIDs.forEach(i -> {
            SensorImageWrapper siw = new SensorImageWrapper();
            siw.setSensorID(i);
            List<TbImageMeta> images = TbImageMetaQueryHelper.getImages(pa.getProjID(), i, false);
            if (!CollectionUtil.isNullOrEmpty(images)) {
                if(images.size()>pa.getImageNumber())
                    images=images.subList(0,pa.getImageNumber());
                siw.setSensorImages(images.stream().map(im -> im.toImageWrapper()).collect(Collectors.toList()));
            }
            result.add(siw);
        });
        return result;
    }
}

