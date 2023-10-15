package id.fahrizal.krlcommuterline.data.repository.source

import android.content.Context
import android.content.res.AssetManager
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import id.fahrizal.krlcommuterline.data.model.FastestRoute
import okio.buffer
import okio.source
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject


class DiskRouteEntityData @Inject constructor(
    @ApplicationContext private val context:Context
) : RouteEntityData {

    override suspend fun getRoute(): FastestRoute {
        val content = context.assets.readAssetsFile("assets/all-stations.json")
        val gson = Gson()
        val route: FastestRoute = gson.fromJson(content, FastestRoute::class.java)

        return FastestRoute()
    }

    private fun AssetManager.readAssetsFile(fileName : String): String = open(fileName).bufferedReader().use{it.readText()}

    private fun readJsonFromAssets(context: Context, filePath: String): String? {
        try {
            val source = context.assets.open(filePath).source().buffer()
            return source.readByteString().string(Charset.forName("utf-8"))

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}