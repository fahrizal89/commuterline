package id.fahrizal.krlcommuterline.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "station")
data class StationResult (
    @PrimaryKey
    var id:Int?=null,
    var name:String?="",
    @Ignore //ignore inserting to db
    var route: HashMap<String, StationDetailResult>?=HashMap(),
)