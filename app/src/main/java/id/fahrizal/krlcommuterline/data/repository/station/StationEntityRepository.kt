package id.fahrizal.krlcommuterline.data.repository.station

import id.fahrizal.krlcommuterline.data.model.Station
import id.fahrizal.krlcommuterline.data.repository.station.source.StationEntityData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StationEntityRepository @Inject constructor(
    private val stationEntityData: StationEntityData
) : StationRepository {

    override fun getStations(filter: String): Flow<List<Station>> = stationEntityData.getStations(filter)

    override fun getStation(id: Int): Flow<Station> = stationEntityData.getStation(id)

    override fun saveStations(stations: List<Station>) = stationEntityData.saveStations(stations)
}