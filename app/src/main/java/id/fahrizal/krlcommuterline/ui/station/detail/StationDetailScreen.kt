package id.fahrizal.krlcommuterline.ui.station.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.fahrizal.krlcommuterline.R
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
            is StationDetailViewModel.StationDetailUiState.Loaded -> {
                StationDetail(
                    name = state.station.name,
                    url = state.station.url
                )
            }
            is StationDetailViewModel.StationDetailUiState.Loading -> LoadingWidget()
            is StationDetailViewModel.StationDetailUiState.Error -> ErrorWidget(msg = state.msg)
        }
    }
}

@Composable
fun StationDetail(name:String="", url:String=""){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            fontSize = MaterialTheme.typography.h5.fontSize,
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url.ifEmpty { DEFAULT_IMAGE_URL })
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.baseline_train_24),
            contentDescription = "station_img",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

private const val DEFAULT_IMAGE_URL = "https://raw.githubusercontent.com/fahrizal89/commuterline/main/be/station_tanahabang.jpeg"