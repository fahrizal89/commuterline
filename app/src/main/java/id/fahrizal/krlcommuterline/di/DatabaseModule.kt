package id.fahrizal.krlcommuterline.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.fahrizal.krlcommuterline.data.db.AppDatabase
import id.fahrizal.krlcommuterline.data.db.AppRoomDatabase
import id.fahrizal.krlcommuterline.data.db.dao.StationDao

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideAppRoomDatabase(appDatabase: AppDatabase): AppRoomDatabase {
        return appDatabase.roomDb
    }

    @Provides
    fun provideStopDao(appRoomDatabase: AppRoomDatabase): StationDao {
        return appRoomDatabase.stationDao()
    }
}