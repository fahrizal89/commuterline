package id.fahrizal.krlcommuterline.domain.mapper

import id.fahrizal.krlcommuterline.domain.model.StationCard

object StationCardCodeMapper {

    fun List<StationCard>.filterDestinations(): List<StationCard> {
        var currentGroup = this.size
        for (i:Int in this.lastIndex downTo 1){
            val prevStationCard = this[i-1]
            val currentStationCard = this[i]

            if(!currentStationCard.next.stationCodes.isSameDestinationCode(prevStationCard.next.stationCodes)){
                currentGroup--
            }
            else {
                prevStationCard.next.stationCodes.filterStationCode(currentStationCard.next.stationCodes)
            }
            currentStationCard.groupIndex = currentGroup

        }

        this[0].groupIndex = currentGroup

        return this
    }

    fun List<String>.isSameDestinationCode(newBusInfos:List<String>) : Boolean {
        for(prevBusInfo in this) {
            for(newBusInfo in newBusInfos){
                val prevBusCode = prevBusInfo.split("-")
                val newBusCode = newBusInfo.split("-")
                if(prevBusCode[0] == newBusCode[0]) return true
            }
        }

        return false
    }

    private fun List<String>.filterStationCode(newBusInfos:List<String>) {
        val sameValueArrList = ArrayList<String>()

        for(prevBusInfo in this) {
            for(newBusInfo in newBusInfos){
                val prevBusCode = prevBusInfo.split("-")
                val newBusCode = newBusInfo.split("-")

                if(prevBusCode[0] == newBusCode[0]){
                    sameValueArrList.add(prevBusInfo)
                }
            }
        }

        this as ArrayList
        clear()
        addAll(sameValueArrList)
    }

}