package id.fahrizal.krlcommuterline.ui.station.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import id.fahrizal.krlcommuterline.ui.common.ErrorWidget
import id.fahrizal.krlcommuterline.ui.common.LoadingWidget

@Composable
fun StationDetailScreen(
    modifier: Modifier = Modifier,
    stationId:Int = -1,
    viewModel : StationDetailViewModel = viewModel()
) {
    viewModel.fetchStation(stationId)

    Column (
        modifier = modifier
    ){
        when (val state = viewModel.uiState.collectAsState().value) {
            is StationDetailViewModel.StationDetailUiState.Loaded -> StationDetail()
            is StationDetailViewModel.StationDetailUiState.Loading -> LoadingWidget()
            is StationDetailViewModel.StationDetailUiState.Error -> ErrorWidget(msg = state.msg)
        }
    }
}

@Composable
private fun StationDetail(){

}