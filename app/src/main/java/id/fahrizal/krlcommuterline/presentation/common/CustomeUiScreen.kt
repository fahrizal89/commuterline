package id.fahrizal.krlcommuterline.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ClickableText(label:String, text:String, onClick: () -> Unit){
    Column {
        Text(
            text = label,
            modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            color = Color.Gray
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 8.dp)
                .clickable { onClick() }
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .padding(8.dp, 8.dp, 8.dp, 8.dp)
                    .fillMaxWidth()
            )
            Divider(modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 4.dp))
        }
    }
}