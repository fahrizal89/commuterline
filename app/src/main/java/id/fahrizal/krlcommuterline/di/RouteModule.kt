package id.fahrizal.krlcommuterline.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.fahrizal.krlcommuterline.data.algorithm.DijkstraAlgorithm
import id.fahrizal.krlcommuterline.data.repository.route.RouteEntityRepository
import id.fahrizal.krlcommuterline.data.repository.route.RouteRepository
import id.fahrizal.krlcommuterline.data.repository.route.source.LocalRouteEntityData
import id.fahrizal.krlcommuterline.data.repository.route.source.MockRouteEntityData
import id.fahrizal.krlcommuterline.data.repository.route.source.RouteEntityData
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
    fun provideMockRouteEntityData(mockRouteEntityData: MockRouteEntityData): RouteEntityData {
        return mockRouteEntityData
    }

    @Provides
    @Singleton
    fun provideDijkstraAlgorithm(): DijkstraAlgorithm {
        return DijkstraAlgorithm()
    }
}