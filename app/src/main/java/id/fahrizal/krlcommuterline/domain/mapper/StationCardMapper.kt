package id.fahrizal.krlcommuterline.domain.mapper

import id.fahrizal.krlcommuterline.data.model.StationResult
import id.fahrizal.krlcommuterline.domain.model.StationCard
import id.fahrizal.krlcommuterline.domain.model.StationCardBranch
import id.fahrizal.krlcommuterline.domain.model.StepCardState
import id.fahrizal.krlcommuterline.domain.mapper.StationCardCodeMapper.isSameDestinationCode


object StationCardMapper {

    fun List<StationResult>.toStationCards() : List<StationCard> {
        val stationCards = ArrayList<StationCard>()

        for (i:Int in 0 until this.size){
            val station = this[i]
            val nextStationCardBranch = if(i < this.lastIndex) {
                val stationCodes = this.getStationCodesFromStationResult(i)
                StationCardBranch(
                    id =this[i+1].id ?: -1,
                    name = stationCodes[0].getLastDestination(),
                    stationCodes = stationCodes
                )
            } else {
                StationCardBranch()
            }

            StationCard(
                index = i,
                id = station.id ?: -1,
                name = station.name ?: "",
                next = nextStationCardBranch,
                state = getState(i, this, stationCards, nextStationCardBranch)
            ).let {
                stationCards.add(it)
            }
        }

        return stationCards
    }

    private fun List<StationResult>.getStationCodesFromStationResult(i:Int): List<String>{
        return this[i].branch?.get(this[i+1].id.toString())?.codes ?: ArrayList()
    }

    private fun getState(i:Int, StationResults:List<StationResult>, StationCards : ArrayList<StationCard>, nextStation:StationCardBranch) : StepCardState {
        if( i == 0) return StepCardState.START
        if(i == StationResults.lastIndex) return StepCardState.END

        val prevStationCard = StationCards[i-1].next
        if(!prevStationCard.stationCodes.isSameDestinationCode(nextStation.stationCodes)){
            return StepCardState.TRANSIT
        }

        return StepCardState.STRAIGHT
    }

    private fun String.getLastDestination() : String = this.split("-")[1]
}