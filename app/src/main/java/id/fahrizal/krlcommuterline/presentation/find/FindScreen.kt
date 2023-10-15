package id.fahrizal.krlcommuterline.presentation.find

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import id.fahrizal.krlcommuterline.R
import id.fahrizal.krlcommuterline.presentation.common.ClickableText

@Composable
fun FindScreen (){
    Column(modifier = Modifier.fillMaxWidth()) {
        ClickableText(
            label = stringResource(id = R.string.from),
            text = "",
            onClick = {
                //pending code

            })

        ClickableText(
            label = stringResource(id = R.string.to),
            text = "",
            onClick = {
                //pending code
            })

        Button(
            modifier = Modifier
                .padding(top = 22.dp, start = 22.dp, end = 22.dp)
                .fillMaxWidth(),
            onClick = {
                //pending code
            })
        {
            Text(text = "Find")
        }

        Divider(modifier = Modifier.padding(top = 8.dp))
    }
}