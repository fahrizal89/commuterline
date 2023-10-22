package id.fahrizal.krlcommuterline.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import id.fahrizal.krlcommuterline.R


@Composable
fun DotCircle(modifier: Modifier, size:Int = 18, color: Color){
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .width(size.dp)
                .height(size.dp),
            painter = painterResource(R.drawable.baseline_circle_24),
            contentDescription = "img_corner_circle",
            colorFilter = ColorFilter.tint(color)
        )
    }
}

@Composable
fun VerticalLine(modifier: Modifier = Modifier, width:Dp = 18.dp, height:Dp, color: Color = Color.DarkGray){
    Canvas(modifier = modifier) {
        drawLine(
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = 0f, y = height.toPx()),
            color = color,
            strokeWidth = width.toPx()
        )
    }
}