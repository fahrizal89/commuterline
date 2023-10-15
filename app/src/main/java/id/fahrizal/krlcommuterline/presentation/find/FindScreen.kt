package id.fahrizal.krlcommuterline.presentation.find

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import id.fahrizal.krlcommuterline.R
import id.fahrizal.krlcommuterline.data.model.StationResult
import id.fahrizal.krlcommuterline.presentation.common.ClickableText
import id.fahrizal.krlcommuterline.presentation.common.ErrorWidget
import id.fahrizal.krlcommuterline.presentation.common.LoadingWidget
import id.fahrizal.krlcommuterline.presentation.guide.GuideWidget

@Composable
fun FindScreen (viewModel: FindViewModel){
    val from = remember{ mutableStateOf(StationResult()) }
    val to = remember{ mutableStateOf(StationResult()) }

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
                viewModel.find(0,2)
            })
        {
            Text(text = "Find")
        }

        Divider(modifier = Modifier.padding(top = 8.dp))

        when (val state = viewModel.uiState.collectAsState().value) {
            is FindViewModel.FindUiState.Loaded -> GuideWidget(state.stationCards)
            is FindViewModel.FindUiState.Loading -> LoadingWidget()
            is FindViewModel.FindUiState.Error -> ErrorWidget(msg = state.msg)
            is FindViewModel.FindUiState.SelectedFrom -> from.value = state.stationResult
            is FindViewModel.FindUiState.SelectedTo -> to.value = state.stationResult
        }
    }
}