package services;

import data.proj.TbTps2ObserverStation;
import data.request.parameters.AddAndUpdateTps2ObserverStationParameter;
import data.request.parameters.GetAndDeleteTps2ObserverStationParameter;
import dataDAO.DataHelper;
import dataDAO.query.helper.Tps2QueryHelper;

import java.util.List;

/**
 * Created by admin on 2016/4/22.
 */
public class Tps2ObserverStationService {
    public List<TbTps2ObserverStation> getObserverStationList(int projID, int stationID) {
        return Tps2QueryHelper.getObserverStation(projID, stationID);
    }

    public Integer addObserverStation(AddAndUpdateTps2ObserverStationParameter pa) {
        return DataHelper.add(pa.getData(), pa.getProjID());
    }

    public void updateObserverStation(AddAndUpdateTps2ObserverStationParameter pa) {
        DataHelper.update(pa.getData(), pa.getProjID());
    }

    public void deleteObserverStation(GetAndDeleteTps2ObserverStationParameter pa) {
        TbTps2ObserverStation station = new TbTps2ObserverStation();
        station.setId(pa.getId());
        DataHelper.delete(station, pa.getProjID());
    }
}
