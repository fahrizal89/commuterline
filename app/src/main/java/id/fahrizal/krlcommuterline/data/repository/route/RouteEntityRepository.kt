package id.fahrizal.krlcommuterline.data.repository.route

import id.fahrizal.krlcommuterline.data.algorithm.DijkstraAlgorithm
import id.fahrizal.krlcommuterline.data.model.Route
import id.fahrizal.krlcommuterline.data.repository.route.factory.RouteFactory
import id.fahrizal.krlcommuterline.data.repository.route.factory.Source
import javax.inject.Inject

class RouteEntityRepository @Inject constructor(
    private val routeFactory: RouteFactory,
    private val dijkstraAlgorithm: DijkstraAlgorithm
): RouteRepository {

    override suspend fun getRoute() : Route{
        val routeFromDb = routeFactory.create(Source.LOCAL).getRoute()

        if(routeFromDb.nodes.isEmpty()){
            val routeFromRemote = routeFactory.create(Source.REMOTE).getRoute()
            routeFactory.create(Source.LOCAL).setRoute(routeFromRemote)

            return routeFromRemote
        }

        return routeFromDb
    }

    override suspend fun findShortestRoute(stationIdFrom:Int, stationIdTo:Int): Route {
        return dijkstraAlgorithm.find(stationIdFrom, stationIdTo)
    }
}