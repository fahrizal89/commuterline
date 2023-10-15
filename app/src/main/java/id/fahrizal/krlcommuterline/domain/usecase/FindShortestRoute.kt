package id.fahrizal.krlcommuterline.domain.usecase

import id.fahrizal.krlcommuterline.data.repository.RouteRepository
import id.fahrizal.krlcommuterline.domain.mapper.StationCardMapper.toStationCards
import id.fahrizal.krlcommuterline.domain.mapper.StationCardCodeMapper.filterDestinations
import id.fahrizal.krlcommuterline.domain.model.StationCard
import javax.inject.Inject

class FindShortestRoute @Inject constructor(
    private val routeRepository: RouteRepository
) {

    suspend operator fun invoke(from: Int, to: Int): List<StationCard> {
        val route = routeRepository.findShortestRoute(from, to)

        return route.nodes.toStationCards().filterDestinations()
    }
}