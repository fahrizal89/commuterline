package id.fahrizal.krlcommuterline.data.repository.route.source

import id.fahrizal.krlcommuterline.data.algorithm.DijkstraAlgorithm
import id.fahrizal.krlcommuterline.data.model.Route
import javax.inject.Inject


class LocalRouteEntityData @Inject constructor(
    private val dijkstraAlgorithm: DijkstraAlgorithm
) : RouteEntityData {

    override suspend fun getRoute(): Route {
        return Route() //pending code
    }

    override suspend fun setRoute(route: Route) {
        dijkstraAlgorithm.graph.apply {
            clear()
            addAll(route.nodes)
        }
    }

    override suspend fun findShortestRoute(stationIdFrom: Int, stationIdTo: Int): Route {
        return dijkstraAlgorithm.find(stationIdFrom, stationIdTo)
    }
}