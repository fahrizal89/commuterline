package id.fahrizal.krlcommuterline.ui.find.station

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
class FindStationViewModel @Inject constructor(
    private val ioCoroutineDispatcher: CoroutineDispatcher,
    private val findStation: FindStation
) : ViewModel(){

    private val _uiState = MutableStateFlow<FindStationUiState>(FindStationUiState.Loaded())
    val uiState: StateFlow<FindStationUiState> = _uiState

    fun search(text:String) {
        viewModelScope.launch(ioCoroutineDispatcher) {
            findStation(text)
                .catch {
                    Timber.e(it)
                    _uiState.value = FindStationUiState.Error(it.toString())
                }
                .collect {
                    _uiState.value = FindStationUiState.Loaded(it)
                }
        }
    }

    sealed class FindStationUiState {
        object Loading : FindStationUiState()
        class Loaded(val stations: List<Station> = ArrayList()) : FindStationUiState()
        class Error(val msg:String) : FindStationUiState()
    }
}