package id.fahrizal.krlcommuterline.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import id.fahrizal.krlcommuterline.data.db.dao.StationDao
import id.fahrizal.krlcommuterline.data.model.Station

@Database(
    version = 1,
    entities = [Station::class],
    exportSchema = true
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun stationDao(): StationDao
}