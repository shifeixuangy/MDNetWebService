package services;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import commonUtil.ImageUtil;
import commonUtil.PathUtil;
import data.proj.CheckImage;
import data.proj.CheckVideo;
import data.request.parameters.*;
import dataDAO.DataHelper;
import dataDAO.check.CheckQueryHelper;

import java.util.List;

/**
 * Created by liudongdong on 2015/11/4.
 */
public class CheckVideoService {
    public List<CheckVideo> getVideo(GetCheckVideoParameter pa)
    {
        List<CheckVideo>videos= CheckQueryHelper.getVideo(pa.getProjID(), pa.getCheckID(), pa.getRecordID(), pa.getVideoID());
        if(!CollectionUtil.isNullOrEmpty(videos))
        {
            for(CheckVideo cv:videos)
            {
                cv.setVideoPath(PathUtil.getPath(cv.getVideoPath()));
            }
        }
        return videos;
    }

    public Integer addVideo(AddCheckVideoParameter pa)
    {
        String videoPath= ImageUtil.addVideo(pa);
        CheckVideo ci=new CheckVideo();
        ci.setCheckID(pa.getCheckID());
        ci.setRecordID(pa.getRecordID());
        ci.setVideoPath(videoPath);
        return DataHelper.add(ci, pa.getProjID());
    }

    public void deleteVideo(DeleteCheckVideoParameter pa)
    {
        GetCheckVideoParameter getPa=new GetCheckVideoParameter();
        getPa.setProjID(pa.getProjID());
        getPa.setVideoID(pa.getVideoID());
        List<CheckVideo> videos=getVideo(getPa);
        if(CollectionUtil.isNullOrEmpty(videos))
            return;
        DataHelper.delete(videos.get(0),pa.getProjID());
        String videoPath=videos.get(0).getVideoPath();
        ImageUtil.deleteFile(videoPath);
    }

}
