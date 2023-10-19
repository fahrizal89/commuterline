package id.fahrizal.krlcommuterline.data.repository.route

import id.fahrizal.krlcommuterline.data.model.Route

interface RouteRepository {

    suspend fun getRoute() : Route
    suspend fun findShortestRoute(stationIdFrom:Int, stationIdTo:Int): Route
}