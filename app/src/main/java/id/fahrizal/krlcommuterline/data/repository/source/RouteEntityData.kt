package id.fahrizal.krlcommuterline.data.repository.source

import id.fahrizal.krlcommuterline.data.model.FastestRoute

interface RouteEntityData {

    suspend fun getRoute(): FastestRoute
}