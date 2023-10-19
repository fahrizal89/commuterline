package id.fahrizal.krlcommuterline.data.model

import androidx.room.Entity

@Entity(tableName = "station")
class StationDetailResult (
    var id:Int?=null,
    var distance:Int = 1, //distance
    var next:Int = -1,
    var codes:List<String> = ArrayList()
)