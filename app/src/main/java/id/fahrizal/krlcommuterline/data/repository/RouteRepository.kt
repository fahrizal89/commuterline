package id.fahrizal.krlcommuterline.data.repository

import id.fahrizal.krlcommuterline.data.model.Route

interface RouteRepository {

    suspend fun initRoute()
    suspend fun findShortestRoute(stationIdFrom:Int, stationIdTo:Int): Route
}