package id.fahrizal.krlcommuterline.domain.mapper

import id.fahrizal.krlcommuterline.R

object StationLineColorMapper {

    fun getLineColor(destination:String) : Int = when(destination){
        "1-0" -> R.color.line_green_1
        "1-18" -> R.color.line_green_1
        "2-35" -> R.color.line_blue_1
        "2-0" -> R.color.line_blue_1

        else -> R.color.line_red_1
    }
}