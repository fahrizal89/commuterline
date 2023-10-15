package id.fahrizal.krlcommuterline.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.fahrizal.krlcommuterline.data.repository.RouteEntityRepository
import id.fahrizal.krlcommuterline.data.repository.RouteRepository
import id.fahrizal.krlcommuterline.data.repository.source.DiskRouteEntityData
import id.fahrizal.krlcommuterline.data.repository.source.RouteEntityData
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
    fun provideDiskRouteEntityData(diskRouteEntityData: DiskRouteEntityData): RouteEntityData {
        return diskRouteEntityData
    }
}