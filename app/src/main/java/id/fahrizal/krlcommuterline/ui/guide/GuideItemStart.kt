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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.fahrizal.krlcommuterline.R

@Composable
fun GuideItemStart(
    name:String,
    destinationName:String,
    colorLineInt :Int,
    colorDotInt :Int= R.color.white,
    paddingLeft:Int
){
    Row(modifier = Modifier.padding(start = paddingLeft.dp)) {
        Box(
            modifier = Modifier.width(52.dp),
        ) {
            LineWithDot(
                modifier = Modifier
                    .width(44.dp)
                    .padding(start = 16.dp, top = 20.dp),
                lineColorInt = colorLineInt,
                heightInt = 42
            )
            Image(
                modifier = Modifier
                    .width(52.dp)
                    .padding(start = 14.dp, end = 6.dp, top = 10.dp),
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
                text = name,
                modifier = Modifier.padding(end=2.dp, bottom = 2.dp, top = 10.dp),
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold
            )

            Row (modifier = Modifier.padding(start = 2.dp)) {
                Text(
                    text = stringResource(id = R.string.destination_title),
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
}