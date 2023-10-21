package id.fahrizal.krlcommuterline.ui.guide

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.fahrizal.krlcommuterline.R

@Composable
fun GuideItemTransit(
    name:String,
    destinationName:String,
    colorLineInt :Int,
    colorTransitLineInt :Int,
    colorDotInt :Int= R.color.white,
    paddingLeft:Int
){
    Row(modifier = Modifier.padding(start = paddingLeft.dp)) {
        Box(
            modifier = Modifier.width(100.dp),
        ) {

            TransitLine(colorTransitLineInt)

            LineWithDot(
                modifier = Modifier
                    .width(44.dp)
                    .padding(start = 16.dp),
                lineColorInt = colorLineInt,
                heightInt = 24
            )
            Image(
                modifier = Modifier
                    .width(60.dp)
                    .padding(start = 2.dp, end = 2.dp, top = 10.dp),
                painter = painterResource(R.drawable.baseline_circle_24),
                contentDescription = "img_circle",
                colorFilter = ColorFilter.tint(colorResource(id = colorLineInt))
            )

            Image(
                modifier = Modifier
                    .width(44.dp)
                    .padding(start = 22.dp, end = 6.dp, top = 14.dp),
                painter = painterResource(R.drawable.baseline_circle_24),
                contentDescription = "img_circle",
                colorFilter = ColorFilter.tint(colorResource(id = colorDotInt))
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
    LineWithDot(
        modifier = Modifier
            .width(44.dp)
            .padding(start = 16.dp, top = 24.dp),
        lineColorInt = androidx.appcompat.R.color.material_grey_300,
        heightInt = 36
    )

    LineWithDot(
        modifier = Modifier
            .width(80.dp)
            .padding(start = 20.dp, top = 22.dp)
            .graphicsLayer {
                this.rotationZ = 30f
            },
        lineColorInt = colorTransitLineInt,
        heightInt = 18
    )

    LineWithDot(
        modifier = Modifier
            .width(84.dp)
            .padding(start = 56.dp, top = 44.dp),
        lineColorInt = colorTransitLineInt,
        heightInt = 62
    )

    Image(
        modifier = Modifier
            .width(100.dp)
            .padding(start = 40.dp, top = 32.dp),
        painter = painterResource(R.drawable.baseline_circle_24),
        contentDescription = "img_corner_circle",
        colorFilter = ColorFilter.tint(colorResource(id = colorTransitLineInt))
    )
}