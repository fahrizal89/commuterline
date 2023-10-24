package id.fahrizal.krlcommuterline.ui.find.route

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.fahrizal.krlcommuterline.R
import id.fahrizal.krlcommuterline.data.model.Station
import id.fahrizal.krlcommuterline.ui.common.ClickableText
import id.fahrizal.krlcommuterline.ui.common.ErrorWidget
import id.fahrizal.krlcommuterline.ui.common.LoadingWidget
import id.fahrizal.krlcommuterline.ui.guide.GuideWidget

@Composable
fun FindScreen (
    modifier: Modifier = Modifier,
    viewModel: FindRouteViewModel = viewModel(),
    onTxtFromClicked: ()->Unit = {},
    onTxtToClicked: ()->Unit = {}
){
    var from by rememberSaveable { mutableStateOf(Station()) }
    var to by rememberSaveable{ mutableStateOf(Station()) }

    Column(modifier = modifier) {
        ClickableText(
            label = stringResource(id = R.string.from),
            text = from.name,
            hint = stringResource(id = R.string.select_station_hint),
            onClick = onTxtFromClicked
        )

        ClickableText(
            label = stringResource(id = R.string.to),
            text = to.name,
            hint = stringResource(id = R.string.select_station_hint),
            onClick = onTxtToClicked
        )

        Button(
            modifier = Modifier
                .padding(top = 22.dp, start = 22.dp, end = 22.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel.find(from.id, to.id)
            })
        {
            Text(text = stringResource(id = R.string.find_route))
        }

        Divider(modifier = Modifier.padding(top = 8.dp))

        when (val state = viewModel.uiState.collectAsState().value) {
            is FindRouteViewModel.FindUiState.Loaded -> GuideWidget(state.stationCards)
            is FindRouteViewModel.FindUiState.Loading -> LoadingWidget()
            is FindRouteViewModel.FindUiState.Error -> ErrorWidget(msg = state.msg)
            is FindRouteViewModel.FindUiState.SelectedStationFrom -> from = state.station
            is FindRouteViewModel.FindUiState.SelectedStationTo -> to = state.station
        }
    }
}