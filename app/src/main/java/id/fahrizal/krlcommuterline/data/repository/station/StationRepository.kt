package id.fahrizal.krlcommuterline.data.repository.station

import id.fahrizal.krlcommuterline.data.model.Station
import kotlinx.coroutines.flow.Flow

interface StationRepository {

    fun getStations(filter:String) : Flow<List<Station>>

    fun getStation(id:Int) : Flow<Station>

    fun saveStations(stations: List<Station>)
}