package id.fahrizal.krlcommuterline.domain.model


class StationCard (
    val index:Int,
    val id:Int,
    val name:String,
    val next:StationCardBranch,
    val state:StepCardState = StepCardState.STRAIGHT,
    var groupIndex :Int = 0
)

class StationCardBranch(
    val id: Int=-1,
    val stationCodes: List<String> = ArrayList()
)

enum class StepCardState {
    START, STRAIGHT, TRANSIT, END
}