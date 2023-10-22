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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import id.fahrizal.krlcommuterline.R


@Composable
fun LoadingWidget(){
    Text(text = "Loading...")
}

@Composable
fun ErrorWidget(msg:String){
    Text(text = msg)
}
@Composable
fun IconEditText(
    modifier: Modifier,
    text: String="",
    onTextChanged: (String) -> Unit,
    label: String="",
    hint:String="",
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
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
                onTextChanged(it.text)
            },
            placeholder = { Text(hint) },
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            modifier = modifier
        )
    }
}

@Composable
fun ClickableText(label:String, text:String="", onClick: () -> Unit){
    Column {
        Text(
            text = label,
            modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
            fontSize = MaterialTheme.typography.caption.fontSize,
            color = colorResource(id = R.color.teal_700),
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