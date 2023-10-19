package id.fahrizal.krlcommuterline.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.fahrizal.krlcommuterline.data.model.Station
import kotlinx.coroutines.flow.Flow

@Dao
interface StationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStations(stations : List<Station>)

    @Query("SELECT * FROM station WHERE name LIKE :name COLLATE NOCASE LIMIT 10")
    fun getStations(name:String): Flow<List<Station>>

    @Query("SELECT * FROM station WHERE id = :id")
    fun getStation(id:Int): Flow<Station>
}