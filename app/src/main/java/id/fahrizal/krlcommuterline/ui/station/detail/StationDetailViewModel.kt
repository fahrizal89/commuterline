package id.fahrizal.krlcommuterline.ui.station.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.fahrizal.krlcommuterline.data.model.Station
import id.fahrizal.krlcommuterline.domain.usecase.FindStation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StationDetailViewModel @Inject constructor(
    private val ioCoroutineDispatcher: CoroutineDispatcher,
    private val findStation: FindStation
) : ViewModel() {

    private val _uiState = MutableStateFlow<StationDetailUiState>(StationDetailUiState.Loading)
    val uiState: StateFlow<StationDetailUiState> = _uiState

    fun fetchStation(id:Int) {
        viewModelScope.launch(ioCoroutineDispatcher) {
            findStation(id)
                .catch {
                    Timber.e(it)
                    _uiState.value = StationDetailUiState.Error(it.toString())
                }
                .collect {
                    _uiState.value = StationDetailUiState.Loaded(it)
                }
        }

        _uiState.value = StationDetailUiState.Loaded()
    }

    sealed class StationDetailUiState {
        object Loading : StationDetailUiState()
        class Loaded(val station: Station = Station()) : StationDetailUiState()
        class Error(val msg:String) : StationDetailUiState()
    }
}