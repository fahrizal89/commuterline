package id.fahrizal.krlcommuterline.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
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
fun DebouncedEditText(
    modifier: Modifier,
    text: String="",
    delayInMillis: Long= 2000,
    onTextChanged: (String) -> Unit,
    label: String="",
    hint:String="",
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean = true,
) {
    val internalText = remember { mutableStateOf(text) }
    val debouncedText = remember { mutableStateOf("") }
    val prevDebouncedText = remember { mutableStateOf("") }
    val job = remember { mutableStateOf<Job?>(null) }

    var textField by remember {
        mutableStateOf(TextFieldValue(text))
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
            value = textField,
            onValueChange = {
                textField = it
                internalText.value = it.text
                // Cancel the previous job if it exists
                job.value?.cancel()

                // Start a new debounce job
                job.value = MainScope().launch {
                    delay(delayInMillis) // Debounce delay: 2000 milliseconds (2 seconds)
                    debouncedText.value = it.text
                }
            },
            placeholder = { Text(hint) },
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            enabled = enabled,
            modifier = modifier
        )
    }

    if(prevDebouncedText.value != debouncedText.value) {
        onTextChanged(debouncedText.value)
        prevDebouncedText.value = debouncedText.value
    }
}

@Composable
fun ClickableText(label:String, text:String="", onClick: () -> Unit){
    Column {
        androidx.compose.material3.Text(
            text = label,
            modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
            fontSize = MaterialTheme.typography.caption.fontSize,
            color = Color.Gray
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 8.dp)
                .clickable { onClick() }
        ) {
            androidx.compose.material3.Text(
                text = text,
                modifier = Modifier
                    .padding(8.dp, 8.dp, 8.dp, 8.dp)
                    .fillMaxWidth()
            )
            Divider(modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 4.dp))
        }
    }
}