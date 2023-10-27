package id.fahrizal.krlcommuterline.data.repository.route.source.remote

import id.fahrizal.krlcommuterline.data.model.Route
import id.fahrizal.krlcommuterline.data.repository.route.source.RouteEntityData
import javax.inject.Inject

class RemoteRouteEntityData @Inject constructor(
    private val routeApi: RouteApi
) : RouteEntityData {

    override suspend fun getRoute(): Route {
        return routeApi.getRoute()
    }

    override suspend fun setRoute(route: Route) {
        //no op
    }

    override suspend fun findShortestRoute(stationIdFrom: Int, stationIdTo: Int): Route = Route()
}