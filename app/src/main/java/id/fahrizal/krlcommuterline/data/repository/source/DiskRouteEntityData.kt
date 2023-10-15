package id.fahrizal.krlcommuterline.data.repository.source

import android.content.Context
import android.content.res.AssetManager
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import id.fahrizal.krlcommuterline.data.model.FastestRoute
import javax.inject.Inject


class DiskRouteEntityData @Inject constructor(
    @ApplicationContext private val context:Context
) : RouteEntityData {

    override suspend fun getRoute(): FastestRoute {
        val content = context.assets.readAssetsFile("all-stations.json")
        return Gson().fromJson(content, FastestRoute::class.java)
    }

    private fun AssetManager.readAssetsFile(fileName : String): String = open(fileName).bufferedReader().use{it.readText()}
}