package id.fahrizal.krlcommuterline.data.repository.route.source

import id.fahrizal.krlcommuterline.data.model.Route

interface RouteEntityData {

    suspend fun getRoute(): Route

    suspend fun setRoute(route: Route)

    suspend fun findShortestRoute(stationIdFrom:Int, stationIdTo:Int): Route
}