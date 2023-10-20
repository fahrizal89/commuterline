package id.fahrizal.krlcommuterline.domain.mapper

import id.fahrizal.krlcommuterline.domain.model.StationCard
import id.fahrizal.krlcommuterline.domain.model.StepCardState

object StationCardCodeMapper {

    fun List<StationCard>.filterDestinationAndSetColor(): List<StationCard> {
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

            //set color
            when(currentStationCard.state){
                StepCardState.START -> {
                    currentStationCard.lineColor = StationLineColorMapper.getLineColor(currentStationCard.next.stationCodes[0])
                }

                StepCardState.STRAIGHT -> {
                    currentStationCard.lineColor = StationLineColorMapper.getLineColor(currentStationCard.next.stationCodes[0])
                }

                StepCardState.TRANSIT -> {
                    currentStationCard.lineColor = StationLineColorMapper.getLineColor(prevStationCard.next.stationCodes[0])
                    currentStationCard.lineTransitColor = StationLineColorMapper.getLineColor(currentStationCard.next.stationCodes[0])
                }

                StepCardState.END -> {
                    currentStationCard.lineColor = StationLineColorMapper.getLineColor(prevStationCard.next.stationCodes[0])
                }
            }

        }

        this[0].let { firstStation->
            firstStation.groupIndex = currentGroup
            firstStation.lineColor = StationLineColorMapper.getLineColor(firstStation.next.stationCodes[0])
        }

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