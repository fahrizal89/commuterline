package id.fahrizal.krlcommuterline.presentation.find

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.fahrizal.krlcommuterline.domain.usecase.InitRoute
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FindViewModel @Inject constructor(
    private val ioCoroutineDispatcher: CoroutineDispatcher,
    private val initRoute: InitRoute
): ViewModel(){

    init {
        viewModelScope.launch(ioCoroutineDispatcher) {
            try {
                initRoute()
            }
            catch (e: Exception){
                Timber.e(e)
            }
        }
    }

}