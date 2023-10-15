package id.fahrizal.krlcommuterline.data.repository.source

import id.fahrizal.krlcommuterline.data.model.Route
import javax.inject.Inject


class LocalRouteEntityData @Inject constructor(

) : RouteEntityData {

    override suspend fun getRoute(): Route {
        return Route() //pending code
    }

    override suspend fun setRoute(route: Route) {
        //pending code
    }
}