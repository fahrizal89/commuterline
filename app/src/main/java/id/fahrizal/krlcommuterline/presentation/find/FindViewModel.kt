package id.fahrizal.krlcommuterline.presentation.find

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.fahrizal.krlcommuterline.data.model.StationResult
import id.fahrizal.krlcommuterline.domain.model.StationCard
import id.fahrizal.krlcommuterline.domain.usecase.FindShortestRoute
import id.fahrizal.krlcommuterline.domain.usecase.InitRoute
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FindViewModel @Inject constructor(
    private val ioCoroutineDispatcher: CoroutineDispatcher,
    private val initRoute: InitRoute,
    private val findShortestRoute: FindShortestRoute
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

    sealed class FindUiState {
        object Loading : FindUiState()
        class Loaded(val stationCards : List<StationCard> = ArrayList()) : FindUiState()
        class Error(val msg:String) : FindUiState()
        class SelectedFrom(val stationResult: StationResult) : FindUiState()
        class SelectedTo(val stationResult: StationResult) : FindUiState()
    }

}