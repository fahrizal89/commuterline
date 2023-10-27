package id.fahrizal.krlcommuterline.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.fahrizal.krlcommuterline.data.algorithm.DijkstraAlgorithm
import id.fahrizal.krlcommuterline.data.repository.route.RouteEntityRepository
import id.fahrizal.krlcommuterline.data.repository.route.RouteRepository
import id.fahrizal.krlcommuterline.data.repository.route.source.local.LocalRouteEntityData
import id.fahrizal.krlcommuterline.data.repository.route.source.RouteEntityData
import id.fahrizal.krlcommuterline.data.repository.route.source.remote.RouteApi
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RouteModule {

    @Provides
    @Singleton
    fun provideRouteRepository(routeEntityRepository: RouteEntityRepository): RouteRepository {
        return routeEntityRepository
    }

    @Provides
    @Singleton
    fun provideLocalRouteEntityData(localRouteEntityData: LocalRouteEntityData): RouteEntityData {
        return localRouteEntityData
    }

    @Provides
    @Singleton
    fun provideDijkstraAlgorithm(): DijkstraAlgorithm {
        return DijkstraAlgorithm()
    }

    @Provides
    @Singleton
    fun provideRouteApi(retrofit: Retrofit): RouteApi {
        return retrofit.create(RouteApi::class.java)
    }
}