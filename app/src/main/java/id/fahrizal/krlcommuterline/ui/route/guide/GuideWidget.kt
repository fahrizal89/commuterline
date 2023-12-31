package id.fahrizal.krlcommuterline.ui.route.guide

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.fahrizal.krlcommuterline.domain.model.StationCard
import id.fahrizal.krlcommuterline.domain.model.StepCardState

@Composable
fun GuideWidget (
    StationCards : List<StationCard> = ArrayList(),
    onClick : (Int)->Unit
){
    if(StationCards.isEmpty()) return

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp)
    ) {
        val minGroupIndex = StationCards[0].groupIndex

        items(items = StationCards, key = { it.index }) { stationCard->
            val paddingLeft = (stationCard.groupIndex - minGroupIndex) * 40
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(stationCard.id) }
            ) {
                when (stationCard.state){
                    StepCardState.START -> GuideItemStart(
                        name = stationCard.name,
                        destinationName = stationCard.next.name,
                        colorLineInt = stationCard.lineColor,
                        paddingLeft = paddingLeft
                    )

                    StepCardState.STRAIGHT -> GuideItemStraight(
                        name = stationCard.name,
                        colorLineInt = stationCard.lineColor,
                        paddingLeft = paddingLeft
                    )

                    StepCardState.TRANSIT -> GuideItemTransit(
                        name = stationCard.name,
                        destinationName = stationCard.next.name,
                        colorLineInt = stationCard.lineColor,
                        colorTransitLineInt = stationCard.lineTransitColor,
                        paddingLeft = paddingLeft
                    )

                    StepCardState.END -> GuideItemEnd(
                        name = stationCard.name,
                        colorLineInt = stationCard.lineColor,
                        paddingLeft = paddingLeft
                    )
                }
            }
        }
    }
}