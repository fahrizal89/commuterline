package id.fahrizal.krlcommuterline.ui.guide

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.fahrizal.krlcommuterline.domain.model.StationCard
import id.fahrizal.krlcommuterline.domain.model.StepCardState

@Composable
fun GuideWidget (
    StationCards : List<StationCard> = ArrayList()
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
            when (stationCard.state){
                StepCardState.START -> GuideItemStart(
                    name = stationCard.name,
                    colorLineInt = stationCard.lineColor,
                    paddingLeft = paddingLeft
                )

                StepCardState.STRAIGHT -> GuideItemStraight(
                    stationCard.name,
                    colorLineInt = stationCard.lineColor,
                    paddingLeft = paddingLeft
                )

                StepCardState.TRANSIT -> GuideItemTransit(
                    stationCard.name,
                    colorLineInt = stationCard.lineColor,
                    colorTransitLineInt = stationCard.lineTransitColor,
                    paddingLeft = paddingLeft
                )

                StepCardState.END -> GuideItemEnd(
                    stationCard.name,
                    colorLineInt = stationCard.lineColor,
                    paddingLeft = paddingLeft
                )
            }
        }
    }
}

@Composable
fun LineWithDot(
    modifier :Modifier = Modifier,
    lineColorInt: Int,
    heightInt:Int = 40
){
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            color = colorResource(id = lineColorInt),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp)
        ){
            Text(text = "", textAlign = TextAlign.Center, modifier = Modifier.height(heightInt.dp))
        }
    }
}