package id.fahrizal.krlcommuterline.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun LoadingWidget(){
    Text(text = "Loading...")
}

@Composable
fun ErrorWidget(msg:String){
    Text(text = msg)
}
@Composable
fun DebounceTextField(
    modifier: Modifier,
    onTextChanged: (String) -> Unit,
    label: String="",
    hint:String="",
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    var text by remember { mutableStateOf("") }
    val prevDebouncedText = remember { mutableStateOf("") }

    text.useDebounce{
        if(prevDebouncedText.value != text) {
            onTextChanged(it)
        }
    }

    val keyboardOptions: KeyboardOptions = if (singleLine) {
        KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = keyboardType,
            capitalization = KeyboardCapitalization.Sentences
        )
    } else {
        KeyboardOptions(imeAction = ImeAction.Default, keyboardType = keyboardType)
    }

    Text(
        text = label,
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
        color = MaterialTheme.colors.primary
    )
    Column {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
            },
            placeholder = { Text(hint) },
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            modifier = modifier
        )
    }
}

@Composable
private fun <T> T.useDebounce(
    delayMillis: Long = 400L,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onChange: (T) -> Unit
): T{
    val state by rememberUpdatedState(this)

    DisposableEffect(state){
        val job = coroutineScope.launch {
            delay(delayMillis)
            onChange(state)
        }
        onDispose {
            job.cancel()
        }
    }
    return state
}

@Composable
fun ClickableText(
    modifier :Modifier=Modifier,
    text:String ="",
    hint:String ="",
    @DrawableRes iconResource : Int? = null,
    onClick: () -> Unit
) {
    val fontColor = if(text != "") Color.DarkGray else Color.Gray

    Column (modifier = modifier){
        Row {
            if(iconResource != null) {
                Image(
                    modifier = Modifier.padding(start = 4.dp, top = 8.dp, end = 4.dp),
                    painter = painterResource(iconResource),
                    contentDescription = "clickable-text-icon"
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp)
                    .clickable { onClick() }
            ) {
                Text(
                    text = if (text != "") text else hint,
                    color = fontColor,
                    fontWeight = if (text != "") FontWeight.Bold else null,
                    modifier = Modifier
                        .padding(start = 2.dp, top = 12.dp, bottom = 12.dp, end = 12.dp)
                        .fillMaxWidth()
                )
                Divider()
            }
        }
    }
}