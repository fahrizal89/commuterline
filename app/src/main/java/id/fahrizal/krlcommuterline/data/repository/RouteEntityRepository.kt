package id.fahrizal.krlcommuterline.data.repository

import id.fahrizal.krlcommuterline.data.algorithm.DijkstraAlgorithm
import id.fahrizal.krlcommuterline.data.model.Route
import id.fahrizal.krlcommuterline.data.repository.factory.RouteFactory
import id.fahrizal.krlcommuterline.data.repository.factory.Source
import javax.inject.Inject

class RouteEntityRepository @Inject constructor(
    private val routeFactory: RouteFactory,
    private val dijkstraAlgorithm: DijkstraAlgorithm
): RouteRepository {

    override suspend fun initRoute() {
        val routeFromDb = routeFactory.create(Source.LOCAL).getRoute()

        if(routeFromDb.nodes.isEmpty()){
            //get file from mock data, you can replace with data network
            val routeFromFile = routeFactory.create(Source.MOCK).getRoute()
            routeFactory.create(Source.LOCAL).setRoute(routeFromFile)
        }
    }

    override suspend fun findShortestRoute(stationIdFrom:Int, stationIdTo:Int): Route {
        return dijkstraAlgorithm.find(stationIdFrom, stationIdTo)
    }
}