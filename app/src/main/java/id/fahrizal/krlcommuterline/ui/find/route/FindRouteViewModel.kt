package id.fahrizal.krlcommuterline.ui.find.route

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.fahrizal.krlcommuterline.data.model.Station
import id.fahrizal.krlcommuterline.domain.model.StationCard
import id.fahrizal.krlcommuterline.domain.usecase.FindShortestRoute
import id.fahrizal.krlcommuterline.domain.usecase.FindStation
import id.fahrizal.krlcommuterline.domain.usecase.InitRoute
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FindRouteViewModel @Inject constructor(
    private val ioCoroutineDispatcher: CoroutineDispatcher,
    private val initRoute: InitRoute,
    private val findShortestRoute: FindShortestRoute,
    private val findStation: FindStation
): ViewModel(){

    private val _uiState = MutableStateFlow<FindUiState>(FindUiState.Loading)
    val uiState: StateFlow<FindUiState> = _uiState

    init {
        viewModelScope.launch(ioCoroutineDispatcher) {
            try {
                initRoute()
                _uiState.value = FindUiState.Loaded()
            }
            catch (e: Exception){
                Timber.e(e)
            }
        }
    }

    fun find(stationIdFrom:Int, stationIdTo:Int){
        if(stationIdFrom < 0 || stationIdTo < 0) {
            _uiState.value = FindUiState.Error("Please select a station")
            return
        }

        viewModelScope.launch(ioCoroutineDispatcher) {
            try {
                val stationCards = findShortestRoute(stationIdFrom, stationIdTo)
                _uiState.value = FindUiState.Loaded(stationCards)
            }
            catch (e: Exception){
                Timber.e(e)
                _uiState.value = FindUiState.Error(e.toString())
            }
        }
    }

    fun setStationFrom(stationId: Int){
        viewModelScope.launch(ioCoroutineDispatcher) {
            findStation(stationId)
                .catch {
                    Timber.e(it)
                    _uiState.value = FindUiState.Error(it.toString())
                }
                .collect { station->
                    _uiState.value = FindUiState.SelectedStationFrom(station)
                }
        }
    }

    fun setStationTo(stationId: Int){
        viewModelScope.launch(ioCoroutineDispatcher) {
            findStation(stationId)
                .catch {
                    Timber.e(it)
                    _uiState.value = FindUiState.Error(it.toString())
                }
                .collect { station->
                    _uiState.value = FindUiState.SelectedStationTo(station)
                }
        }
    }

    sealed class FindUiState {
        object Loading : FindUiState()
        class Loaded(val stationCards : List<StationCard> = ArrayList()) : FindUiState()
        class Error(val msg:String) : FindUiState()
        class SelectedStationFrom(val station:Station) : FindUiState()
        class SelectedStationTo(val station:Station) : FindUiState()
    }
}