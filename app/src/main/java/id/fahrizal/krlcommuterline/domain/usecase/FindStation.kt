package id.fahrizal.krlcommuterline.domain.usecase

import id.fahrizal.krlcommuterline.data.model.Station
import id.fahrizal.krlcommuterline.data.repository.station.StationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindStation @Inject constructor(
    private val stationRepository: StationRepository
) {

    operator fun invoke(filter:String): Flow<List<Station>> {
        return stationRepository.getStations("%$filter%")
    }

    operator fun invoke(id:Int): Flow<Station> {
        return stationRepository.getStation(id)
    }
}