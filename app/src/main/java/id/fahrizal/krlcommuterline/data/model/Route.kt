package id.fahrizal.krlcommuterline.data.model

class Route(
    val nodes : List<StationResult> = ArrayList(),
    val distance: Int = 0
)