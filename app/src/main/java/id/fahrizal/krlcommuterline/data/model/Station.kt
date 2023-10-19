package id.fahrizal.krlcommuterline.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "station")
data class Station (
    @PrimaryKey
    val id:Int=-1,
    val name:String=""
) : Parcelable