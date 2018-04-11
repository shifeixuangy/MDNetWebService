package dataDAO;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import data.common.TbLevel;
import data.response.results.GetProjListResult;
import data.response.results.LevelProjWrapper;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2016/3/22.
 */
public class TbLevelUtil {
    public static List<Integer> getSubIDs(int fatherID) {
        List<TbLevel> levels = CommonQueryHelper.getLevels(CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID);
        if (CollectionUtil.isNullOrEmpty(levels))
            return Collections.emptyList();
        List<Integer> result = levels.stream().filter(l -> l.getFatherLevelID() == fatherID).map(l -> l.getId()).collect(Collectors.toList());
        if (CollectionUtil.isNullOrEmpty(result))
            return Collections.emptyList();
        List<Integer> allResult = new LinkedList<>();
        allResult.addAll(result);
        result.stream().forEach(id -> {
            allResult.addAll(getSubIDs(id));
        });
        return allResult;
    }

    public static List<TbLevel> getDirectSubLevel(int fatherID) {
        return CommonQueryHelper.getLevels(CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
                .stream().filter(l -> l.getFatherLevelID() == fatherID).collect(Collectors.toList());
    }

    public static LevelProjWrapper getLevelProj(int levelID, List<GetProjListResult> projInfos) {
        Optional<TbLevel> levelOptional = CommonQueryHelper.getLevels(CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID)
                .stream().filter(l -> l.getId() == levelID).findFirst();
        if(!levelOptional.isPresent())
            return null;
        TbLevel level=levelOptional.get();
        LevelProjWrapper lpw = new LevelProjWrapper();
        lpw.setLevelID(level.getId());
        lpw.setFatherLevelID(level.getFatherLevelID());
        lpw.setLevelName(level.getLevelName());
        lpw.setLevelProjs(projInfos.stream().filter(p->(p.getLevelID()!=null)&&p.getLevelID().equals(levelID))
                .collect(Collectors.toList()));
        List<TbLevel>subLevels=getDirectSubLevel(levelID);
        if(CollectionUtil.isNullOrEmpty(subLevels))
            lpw.setSubLevels(Collections.emptyList());
        else
        {
            List<LevelProjWrapper>subLevelProjs=new LinkedList<>();
            subLevels.forEach(l->{
                LevelProjWrapper subLpw=getLevelProj(l.getId(),projInfos);
                if(subLpw!=null)
                    subLevelProjs.add(subLpw);
            });
            lpw.setSubLevels(subLevelProjs);
        }
        return lpw;
    }
}
