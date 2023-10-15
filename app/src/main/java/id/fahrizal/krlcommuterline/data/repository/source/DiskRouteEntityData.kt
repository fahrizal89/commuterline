package id.fahrizal.krlcommuterline.data.repository.source

import android.content.Context
import android.content.res.AssetManager
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import id.fahrizal.krlcommuterline.data.model.Route
import javax.inject.Inject


class DiskRouteEntityData @Inject constructor(
    @ApplicationContext private val context:Context
) : RouteEntityData {

    override suspend fun getRoute(): Route {
        val content = context.assets.readAssetsFile("all-stations.json")
        return Gson().fromJson(content, Route::class.java)
    }

    private fun AssetManager.readAssetsFile(fileName : String): String = open(fileName).bufferedReader().use{it.readText()}
}