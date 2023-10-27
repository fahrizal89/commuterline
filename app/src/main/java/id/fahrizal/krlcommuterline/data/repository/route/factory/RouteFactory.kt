package id.fahrizal.krlcommuterline.data.repository.route.factory

import id.fahrizal.krlcommuterline.data.repository.route.factory.Source.*
import id.fahrizal.krlcommuterline.data.repository.route.source.local.LocalRouteEntityData
import id.fahrizal.krlcommuterline.data.repository.route.source.RouteEntityData
import id.fahrizal.krlcommuterline.data.repository.route.source.remote.RemoteRouteEntityData
import javax.inject.Inject

class RouteFactory @Inject constructor(
    private val localRouteEntityData: LocalRouteEntityData,
    private val remoteRouteEntityData: RemoteRouteEntityData
) {

    fun create(source: Source) : RouteEntityData {
        return when(source) {
            LOCAL -> localRouteEntityData
            REMOTE -> remoteRouteEntityData
        }
    }
}