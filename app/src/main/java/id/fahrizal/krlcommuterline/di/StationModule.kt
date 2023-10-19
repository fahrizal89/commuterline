package id.fahrizal.krlcommuterline.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.fahrizal.krlcommuterline.data.repository.station.StationEntityRepository
import id.fahrizal.krlcommuterline.data.repository.station.StationRepository
import id.fahrizal.krlcommuterline.data.repository.station.source.StationEntityData
import id.fahrizal.krlcommuterline.data.repository.station.source.local.LocalStationEntityData
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class StationModule {

    @Provides
    @Singleton
    fun provideStationRepository(stationEntityRepository: StationEntityRepository): StationRepository {
        return stationEntityRepository
    }

    @Provides
    @Singleton
    fun provideStationEntityData(localStationEntityData: LocalStationEntityData): StationEntityData {
        return localStationEntityData
    }
}