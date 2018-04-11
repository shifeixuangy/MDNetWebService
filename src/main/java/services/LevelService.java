package services;

import commonUtil.CollectionUtil;
import commonUtil.CommonVariable;
import data.common.TbLevel;
import data.response.results.GetProjListResult;
import data.response.results.LevelProjWrapper;
import dataDAO.CommonQueryHelper;
import dataDAO.DataHelper;
import dataDAO.JDBCExecuteSql;
import dataDAO.TbLevelUtil;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liudongdong on 2016/3/22.
 */
public class LevelService {
    public List<LevelProjWrapper> getLevelProjList(String accessToken) {
        ProjInfoService pis=new ProjInfoService();
        List<GetProjListResult>projInfos= pis.getProjList(accessToken,CommonVariable.DEFAULT_PROJID);
        if(CollectionUtil.isNullOrEmpty(projInfos))
            return Collections.emptyList();
        List<LevelProjWrapper>result=new LinkedList<>();
        LevelProjWrapper lpwNoLevelProj=new LevelProjWrapper();
        lpwNoLevelProj.setLevelID(-1);
        lpwNoLevelProj.setFatherLevelID(-1);
        lpwNoLevelProj.setLevelName("未分级项目");
        lpwNoLevelProj.setLevelProjs(projInfos.stream().filter(p->p.getLevelID()==null).collect(Collectors.toList()));
        result.add(lpwNoLevelProj);
        List<Integer>topLevelIDs=TbLevelUtil.getDirectSubLevel(0).stream().map(l->l.getId()).collect(Collectors.toList());
        if(!CollectionUtil.isNullOrEmpty(topLevelIDs))
        {
            topLevelIDs.stream().forEach(id->{
                LevelProjWrapper lpwSub=TbLevelUtil.getLevelProj(id,projInfos);
                if(lpwSub!=null)
                    result.add(lpwSub);
            });
        }
        return result;
    }

    public List<TbLevel> getLevelList(int fatherLevelID) {
        return CommonQueryHelper.getLevels(CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID).stream()
                .filter(l -> l.getFatherLevelID() == fatherLevelID)
                .collect(Collectors.toList());
    }

    public int addLevel(TbLevel level) {
        return DataHelper.add(level, CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID);
    }

    public void updateLevel(TbLevel level) {
        TbLevel oldLevel = CommonQueryHelper.getLevels(level.getId()).get(0);
        oldLevel.setFatherLevelID(level.getFatherLevelID());
        oldLevel.setLevelName(level.getLevelName());
        oldLevel.autoSetLevelDepth();
        DataHelper.update(oldLevel, CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID);
    }

    public void deleteLevel(int levelID) {
        List<TbLevel> levels = CommonQueryHelper.getLevels(CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID);
        if (CollectionUtil.isNullOrEmpty(levels))
            return;
        List<Integer> subLevelIDs = TbLevelUtil.getSubIDs(levelID);
        if (!CollectionUtil.isNullOrEmpty(subLevelIDs)) {
            StringBuilder sqlBuilder = new StringBuilder(" delete from tb_level where `ID` in (");
            subLevelIDs.forEach(id->{
                sqlBuilder.append(id.toString()+",");
            });
            sqlBuilder.deleteCharAt(sqlBuilder.lastIndexOf(","));
            sqlBuilder.append(")");
            JDBCExecuteSql.executeUpdate(CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID,sqlBuilder.toString());
        }
        TbLevel level = new TbLevel();
        level.setId(levelID);
        DataHelper.delete(level, CommonVariable.DEFAULT_INT_AUTO_INCREMENT_ID);
    }
}
