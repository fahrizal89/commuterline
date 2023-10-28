package id.fahrizal.krlcommuterline.domain.usecase

import id.fahrizal.krlcommuterline.data.model.Route
import id.fahrizal.krlcommuterline.data.repository.route.RouteRepository
import javax.inject.Inject

class FindShortestRoute @Inject constructor(
    private val routeRepository: RouteRepository
) {

    suspend operator fun invoke(from: Int, to: Int): Route {
        return routeRepository.findShortestRoute(from, to)
    }
}