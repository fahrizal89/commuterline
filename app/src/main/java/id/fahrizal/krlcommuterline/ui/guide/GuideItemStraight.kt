package id.fahrizal.krlcommuterline.ui.guide

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import id.fahrizal.krlcommuterline.R
import id.fahrizal.krlcommuterline.ui.common.DotCircle
import id.fahrizal.krlcommuterline.ui.common.VerticalLine

@Composable
fun GuideItemStraight(
    name:String,
    colorLineInt :Int,
    colorDotInt :Int= R.color.white,
    paddingLeft:Int
){
    Row(modifier = Modifier.padding(start = paddingLeft.dp)) {
        Box(
            modifier = Modifier.width(52.dp),
        ) {
            VerticalLine(
                modifier = Modifier.padding(start = 29.dp),
                height = 32.dp,
                color = colorResource(id = colorLineInt)
            )
            DotCircle(
                modifier = Modifier.padding(start = 21.dp, top = 12.dp),
                color = colorResource(id = colorDotInt)
            )

        }

        Text(
            text = name,
            modifier = Modifier.padding(end=2.dp, bottom = 2.dp, top = 12.dp),
            fontSize = MaterialTheme.typography.caption.fontSize,
            color = Color.DarkGray
        )

    }
}