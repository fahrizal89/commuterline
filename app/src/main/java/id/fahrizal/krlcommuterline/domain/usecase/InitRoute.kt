package id.fahrizal.krlcommuterline.domain.usecase

import id.fahrizal.krlcommuterline.data.repository.route.RouteRepository
import id.fahrizal.krlcommuterline.data.repository.station.StationRepository
import id.fahrizal.krlcommuterline.ui.route.find.mapper.StationMapper.toStations
import javax.inject.Inject

class InitRoute @Inject constructor(
    private val routeRepository: RouteRepository,
    private val stationRepository: StationRepository
) {

    suspend operator fun invoke(){
        val route = routeRepository.getRoute()

        stationRepository.saveStations(route.nodes.toStations())
    }
}