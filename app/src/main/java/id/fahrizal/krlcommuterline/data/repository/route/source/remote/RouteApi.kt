package id.fahrizal.krlcommuterline.data.repository.route.source.remote

import id.fahrizal.krlcommuterline.data.model.Route
import retrofit2.http.GET

interface RouteApi {
    @GET("commuterline/main/be/all-stations.json")
    suspend fun getRoute(): Route
}