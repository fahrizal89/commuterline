package id.fahrizal.krlcommuterline.data.repository.route.factory

import id.fahrizal.krlcommuterline.data.repository.route.source.LocalRouteEntityData
import id.fahrizal.krlcommuterline.data.repository.route.source.MockRouteEntityData
import id.fahrizal.krlcommuterline.data.repository.route.source.RouteEntityData
import javax.inject.Inject

class RouteFactory @Inject constructor(
    private val localRouteEntityData: LocalRouteEntityData,
    private val mockRouteEntityData: MockRouteEntityData
) {

    fun create(source: Source) : RouteEntityData {
        return when(source) {
            Source.LOCAL -> localRouteEntityData
            Source.MOCK -> mockRouteEntityData
        }
    }
}