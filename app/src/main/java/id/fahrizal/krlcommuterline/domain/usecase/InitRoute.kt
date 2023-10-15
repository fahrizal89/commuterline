package id.fahrizal.krlcommuterline.domain.usecase

import id.fahrizal.krlcommuterline.data.repository.RouteRepository
import javax.inject.Inject

class InitRoute @Inject constructor(
    private val routeRepository: RouteRepository
) {

    suspend operator fun invoke(){
        routeRepository.initRoute()
    }
}