package id.fahrizal.krlcommuterline.domain.mapper

import id.fahrizal.krlcommuterline.R

object StationLineColorMapper {

    fun getLineColor(destination:String) : Int = when(destination){
        "1-Tanah Abang" -> R.color.line_green_1
        "1-Rangkas Bitung" -> R.color.line_green_1
        "2-Cikarang" -> R.color.line_blue_1
        "2-Tanah Abang" -> R.color.line_blue_1

        else -> R.color.line_red_1
    }
}