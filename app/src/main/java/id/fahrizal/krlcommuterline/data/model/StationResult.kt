package id.fahrizal.krlcommuterline.data.model


data class StationResult (
    var id:Int?=null,
    var name:String?="",
    var branch: HashMap<String, StationDetailResult>?=HashMap(),
)