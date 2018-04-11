package services;

import commonUtil.*;
import data.proj.TbImageMeta;
import data.request.parameters.AddProjImageParameter;
import data.request.parameters.DeleteProjImageParameter;
import data.request.parameters.GetProjImageParameter;
import data.response.results.ImageWrapper;
import data.response.results.ProjImageWrapper;
import dataDAO.CommonQueryHelper;
import dataDAO.DataHelper;
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
public class ProjImageService {
    public int addProjImage(AddProjImageParameter pa) {
        String imagePath = ImageUtil.addImage(pa);
        if (StringUtil.isNullOrEmpty(imagePath))
            return -1;
        TbImageMeta im = new TbImageMeta();
        im.setImagePath(imagePath);
        im.setIsProjImage(true);
        im.setNote(pa.getImageNote());
        im.setProjID(pa.getProjID());
        im.setUploadTime(new Timestamp(System.currentTimeMillis()));
        return DataHelper.add(im, pa.getProjID());
    }

    public void deleteProjImage(DeleteProjImageParameter pa) {
        TbImageMeta im = new TbImageMeta();
        im.setId(pa.getImageID());
        DataHelper.delete(im, pa.getProjID());
    }

    public List<ProjImageWrapper> getProjImage(GetProjImageParameter pa) {
        List<Integer> projIDs;
        if (pa.getProjID() != CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID) {
            projIDs = Arrays.asList(pa.getProjID());
        } else {
            projIDs = CommonQueryHelper.getProjInfo().stream().map(p -> p.getId()).collect(Collectors.toList());
        }
        if (CollectionUtil.isNullOrEmpty(projIDs))
            return Collections.emptyList();
        List<ProjImageWrapper> result = new LinkedList<>();
        projIDs.forEach(i -> {
            ProjImageWrapper piw = new ProjImageWrapper();
            piw.setProjID(i);
            List<TbImageMeta> images = TbImageMetaQueryHelper.getImages(i, 0, true);
            if (!CollectionUtil.isNullOrEmpty(images)) {
                if(images.size()>pa.getImageNumber())
                    images=images.subList(0,pa.getImageNumber());
                piw.setProjImages(images.stream().map(im -> im.toImageWrapper()).collect(Collectors.toList()));
            }
            result.add(piw);
        });
        return result;
    }
}
