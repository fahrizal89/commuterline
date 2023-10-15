package id.fahrizal.krlcommuterline.data.model

class StationDetailResult (
    var id:Int?=null,
    var distance:Int = 1, //distance
    var next:Int = -1,
    var codes:List<String> = ArrayList()
)