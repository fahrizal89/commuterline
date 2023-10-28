package id.fahrizal.krlcommuterline.ui.route.find.mapper

import id.fahrizal.krlcommuterline.data.model.Station
import id.fahrizal.krlcommuterline.data.model.StationResult


object StationMapper {

    fun List<StationResult>.toStations() : List<Station> {
        return ArrayList<Station>().also { stations->
            for(stationResult in this) {
                stations.add(Station(
                    id = stationResult.id ?: -1,
                    name = stationResult.name ?: "")
                )
            }
        }
    }
}