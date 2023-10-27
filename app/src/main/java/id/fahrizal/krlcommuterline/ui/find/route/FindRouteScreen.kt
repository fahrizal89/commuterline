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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.fahrizal.krlcommuterline.R
import id.fahrizal.krlcommuterline.ui.common.ClickableText
import id.fahrizal.krlcommuterline.ui.common.ErrorWidget
import id.fahrizal.krlcommuterline.ui.common.LoadingWidget
import id.fahrizal.krlcommuterline.ui.guide.GuideWidget

@Composable
fun FindScreen (
    modifier: Modifier = Modifier,
    viewModel: FindRouteViewModel = viewModel(),
    onTxtDepartureClicked: ()->Unit = {},
    onTxtDestinationClicked: ()->Unit = {}
){
    var departureId by rememberSaveable { mutableStateOf(-1) }
    var departureName by rememberSaveable { mutableStateOf("") }
    var destinationId by rememberSaveable{ mutableStateOf(-1) }
    var destinationName by rememberSaveable{ mutableStateOf("") }

    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.destination_title),
            color = colorResource(id = R.color.teal_700),
            modifier = Modifier.padding(start = 8.dp)
        )

        ClickableText(
            text = departureName,
            hint = stringResource(id = R.string.select_station_from_hint),
            iconResource = R.drawable.baseline_train_24,
            onClick = onTxtDepartureClicked
        )

        Text(text = "|", modifier = Modifier.padding(start = 12.dp))

        ClickableText(
            text = destinationName,
            hint = stringResource(id = R.string.select_station_to_hint),
            iconResource = R.drawable.baseline_train_24,
            onClick = onTxtDestinationClicked
        )

        Button(
            modifier = Modifier
                .padding(top = 22.dp, start = 22.dp, end = 22.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel.find(departureId, destinationId)
            })
        {
            Text(text = stringResource(id = R.string.find_route))
        }

        Divider(modifier = Modifier.padding(top = 8.dp))

        when (val state = viewModel.uiState.collectAsState().value) {
            is FindRouteViewModel.FindUiState.Loaded -> GuideWidget(state.stationCards)
            is FindRouteViewModel.FindUiState.Loading -> LoadingWidget()
            is FindRouteViewModel.FindUiState.Error -> ErrorWidget(msg = state.msg)
            is FindRouteViewModel.FindUiState.SelectedStationDeparture -> {
                departureId = state.id
                departureName = state.name
            }
            is FindRouteViewModel.FindUiState.SelectedStationDestination -> {
                destinationId = state.id
                destinationName = state.name
            }
        }
    }
}