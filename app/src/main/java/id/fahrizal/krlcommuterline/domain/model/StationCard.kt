package id.fahrizal.krlcommuterline.domain.model

import id.fahrizal.krlcommuterline.R


class StationCard (
    val index:Int,
    val id:Int,
    val name:String,
    val next:StationCardBranch,
    val state:StepCardState = StepCardState.STRAIGHT,
    var groupIndex :Int = 0,
    var lineColor:Int = R.color.teal_700,
    var lineTransitColor:Int = R.color.teal_700
)

class StationCardBranch(
    val id: Int=-1,
    val stationCodes: List<String> = ArrayList()
)

enum class StepCardState {
    START, STRAIGHT, TRANSIT, END
}