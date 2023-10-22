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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import id.fahrizal.krlcommuterline.R
import id.fahrizal.krlcommuterline.ui.common.DotCircle
import id.fahrizal.krlcommuterline.ui.common.VerticalLine
import id.fahrizal.krlcommuterline.ui.common.XLine

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
            modifier = Modifier.width(92.dp),
        ) {
            AnotherLine()
            TransitLine(colorTransitLineInt)

            VerticalLine(
                modifier = Modifier.padding(start = 29.dp),
                height = 10.dp,
                color = colorResource(id = colorLineInt)
            )

            DotCircle(
                modifier = Modifier.padding(start = 14.dp, top = 4.dp),
                size = 30,
                color = colorResource(id = colorLineInt)
            )

            DotCircle(
                modifier = Modifier.padding(start = 21.dp, top = 12.dp),
                color = colorResource(id = colorDotInt)
            )

        }

        Column {
            Text(
                text = name,
                modifier = Modifier.padding(end=2.dp, bottom = 2.dp, top = 2.dp),
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold
            )

            Row {
                Text(
                    text = stringResource(id = R.string.transit_to_title),
                    modifier = Modifier.padding(end=2.dp, top = 2.dp),
                    fontSize = MaterialTheme.typography.caption.fontSize,
                    color = colorResource(id = R.color.teal_700),
                )

                Text(
                    text = destinationName,
                    modifier = Modifier.padding(end=2.dp, top = 2.dp),
                    fontSize = MaterialTheme.typography.caption.fontSize,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}

@Composable
private fun TransitLine(colorTransitLineInt: Int){
    XLine(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 16.dp),
        x = 50.dp,
        height= 26.dp,
        width = 14.dp,
        color = colorResource(id = colorTransitLineInt)
    )

    VerticalLine(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 69.dp, top = 44.dp),
        height= 40.dp,
        color = colorResource(id = colorTransitLineInt)
    )

    //corner
    DotCircle(
        modifier = Modifier.padding(start = 59.dp, top = 33.dp),
        size = 21,
        color = colorResource(id = colorTransitLineInt)
    )
}

@Composable
private fun AnotherLine(){
    VerticalLine(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 29.dp, top = 24.dp),
        width = 12.dp,
        height = 100.dp,
        color = colorResource(id = androidx.appcompat.R.color.material_grey_300)
    )
}