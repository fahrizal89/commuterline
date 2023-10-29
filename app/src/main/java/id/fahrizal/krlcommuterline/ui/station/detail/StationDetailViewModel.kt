package id.fahrizal.krlcommuterline.ui.station.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.fahrizal.krlcommuterline.data.model.Station
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class StationDetailViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow<StationDetailUiState>(StationDetailUiState.Loading)
    val uiState: StateFlow<StationDetailUiState> = _uiState

    fun fetchStation(id:Int) {
        id.toString()

        _uiState.value = StationDetailUiState.Loaded()
    }

    sealed class StationDetailUiState {
        object Loading : StationDetailUiState()
        class Loaded(val station: Station = Station()) : StationDetailUiState()
        class Error(val msg:String) : StationDetailUiState()
    }
}