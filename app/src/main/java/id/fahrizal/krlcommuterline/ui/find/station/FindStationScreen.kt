package id.fahrizal.krlcommuterline.ui.find.station

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.fahrizal.krlcommuterline.R
import id.fahrizal.krlcommuterline.data.model.Station
import id.fahrizal.krlcommuterline.ui.common.IconEditText
import id.fahrizal.krlcommuterline.ui.common.ErrorWidget
import id.fahrizal.krlcommuterline.ui.common.LoadingWidget
import kotlinx.coroutines.delay
import timber.log.Timber

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FindStationScreen(
    modifier: Modifier = Modifier,
    viewModel: FindStationViewModel = viewModel(),
    onSelected: (Int) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current
    val prevDebouncedText = remember { mutableStateOf("") }

    Column(modifier = modifier) {
        IconEditText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 2.dp, 8.dp, 2.dp)
                .focusRequester(focusRequester),
            label = stringResource(id = R.string.find),
            hint = stringResource(id = R.string.find_station_hint),
            onTextChanged = { text->
                Timber.d("search>>$text")
                if(prevDebouncedText.value != text) {
                    viewModel.search(text)
                    prevDebouncedText.value = text
                }
            },
        )

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
            delay(500)
            keyboard?.show()
        }

        when (val state = viewModel.uiState.collectAsState().value) {
            is FindStationViewModel.FindStationUiState.Loaded -> {
                StationList(state.stations) { id ->
                    onSelected(id)
                    //clear current search result
                    (state.stations as ArrayList).clear()
                }
            }
            is FindStationViewModel.FindStationUiState.Error -> ErrorWidget(msg = state.msg)
            is FindStationViewModel.FindStationUiState.Loading -> LoadingWidget()
        }
    }
}

@Composable
private fun StationList(stations: List<Station> = ArrayList(), onSelected: (Int) -> Unit){
    LazyColumn(modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 8.dp)) {
        items(items = stations, key = { it.id }) { station ->
            StationItem(
                id = station.id,
                name = station.name,
                onClick = onSelected
            )
        }
    }
    Divider()
}

@Composable
private fun StationItem(id:Int, name:String, onClick: (Int) -> Unit){
    Column (modifier = Modifier.clickable { onClick(id) }){
        Divider()
        Row(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
            Text(text = name)
        }
    }
}