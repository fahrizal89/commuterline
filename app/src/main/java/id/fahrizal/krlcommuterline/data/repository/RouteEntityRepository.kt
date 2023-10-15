package id.fahrizal.krlcommuterline.data.repository

import id.fahrizal.krlcommuterline.data.repository.source.RouteEntityData
import javax.inject.Inject

class RouteEntityRepository @Inject constructor(
    private val routeEntityData: RouteEntityData
): RouteRepository {

    override suspend fun initRoute() {
        routeEntityData.getRoute()
    }
}