package id.fahrizal.krlcommuterline.data.model


data class StationResult (
    var id:Int?=null,
    var name:String?="",
    var url:String?="",
    var branch: HashMap<String, StationDetailResult>?=HashMap(),
)