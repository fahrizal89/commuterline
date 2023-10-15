package id.fahrizal.krlcommuterline.data.repository.source

import id.fahrizal.krlcommuterline.data.model.Route

interface RouteEntityData {

    suspend fun getRoute(): Route
}