package id.fahrizal.krlcommuterline.ui.guide

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.fahrizal.krlcommuterline.R
import id.fahrizal.krlcommuterline.ui.common.DotCircle
import id.fahrizal.krlcommuterline.ui.common.VerticalLine

@Composable
fun GuideItemTransit(
    name:String,
    destinationName:String,
    colorLineInt :Int,
    colorTransitLineInt :Int,
    colorDotInt :Int= R.color.white,
    paddingLeft:Int
){
    Row(modifier = Modifier.padding(start = paddingLeft.dp, bottom = 8.dp)) {
        Box(
            modifier = Modifier.width(100.dp),
        ) {
            AnotherLine()
            TransitLine(colorTransitLineInt)

            VerticalLine(
                modifier = Modifier.padding(start = 29.dp),
                height = 50f,
                color = colorResource(id = colorLineInt)
            )

            DotCircle(
                modifier = Modifier.padding(start = 14.dp, top = 4.dp),
                size = 30,
                color = colorResource(id = colorLineInt)
            )

            DotCircle(
                modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                color = colorResource(id = colorDotInt)
            )

        }

        Column {
            Text(
                text = stringResource(id = R.string.transit_title),
                modifier = Modifier.padding(end=2.dp, top = 2.dp),
                fontSize = MaterialTheme.typography.caption.fontSize,
                color = colorResource(id = R.color.teal_700),
            )
            
            Text(
                text = name,
                modifier = Modifier.padding(end=2.dp, bottom = 2.dp, top = 2.dp),
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold
            )


            Text(
                text = stringResource(id = R.string.change_destination_title),
                modifier = Modifier.padding(end=2.dp, top = 2.dp),
                fontSize = MaterialTheme.typography.caption.fontSize,
                color = colorResource(id = R.color.teal_700),
            )

            Text(
                text = destinationName,
                modifier = Modifier.padding(end=2.dp, top = 2.dp),
                fontSize = MaterialTheme.typography.caption.fontSize,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold
            )

        }

    }
}

@Composable
private fun TransitLine(colorTransitLineInt: Int){
    Line(
        modifier = Modifier
            .width(80.dp)
            .padding(start = 20.dp, top = 22.dp)
            .graphicsLayer {
                this.rotationZ = 30f
            },
        lineColorInt = colorTransitLineInt,
        heightInt = 18
    )

    VerticalLine(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 70.dp, top = 44.dp),
        height= 140f,
        color = colorResource(id = colorTransitLineInt)
    )

    DotCircle(
        modifier = Modifier.padding(start = 61.dp, top = 33.dp),
        size = 20,
        color = colorResource(id = colorTransitLineInt)
    )
}

@Composable
private fun AnotherLine(){
    Box {
        VerticalLine(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 29.dp, top = 24.dp),
            height = 300f,
            color = colorResource(id = androidx.appcompat.R.color.material_grey_300)
        )

        DotCircle(
            modifier = Modifier.padding(start = 20.dp, top = 64.dp),
            color = colorResource(id = R.color.white)
        )
    }
}