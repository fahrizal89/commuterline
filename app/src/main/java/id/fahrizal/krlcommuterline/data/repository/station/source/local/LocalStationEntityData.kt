package id.fahrizal.krlcommuterline.data.repository.station.source.local

import id.fahrizal.krlcommuterline.data.db.dao.StationDao
import id.fahrizal.krlcommuterline.data.model.Station
import id.fahrizal.krlcommuterline.data.repository.station.source.StationEntityData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalStationEntityData @Inject constructor(
    private val stationDao: StationDao
) : StationEntityData {

    override fun getStations(filter: String): Flow<List<Station>> {
        return stationDao.getStations(filter)
    }

    override fun getStation(id: Int): Flow<Station> {
        return stationDao.getStation(id)
    }

    override fun saveStations(stations: List<Station>) {
        stationDao.insertStations(stations)
    }
}