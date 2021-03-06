package com.example.ablyproject.di

import android.app.Application
import androidx.room.Room
import com.example.ablyproject.data.db.AppDatabase
import com.example.ablyproject.data.db.HomeDatabase
import com.example.ablyproject.data.db.HomeRoomDatabase
import com.example.ablyproject.data.db.dao.BannerDao
import com.example.ablyproject.data.db.dao.FavoriteDao
import com.example.ablyproject.data.db.dao.GoodDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module open class DatabaseModule {
    companion object {
        val instance = DatabaseModule()
    }

    @Singleton @Provides
    fun provideHomeDatabase(
        appDatabase: AppDatabase,
        bannerDao: BannerDao,
        goodDao: GoodDao,
        favoriteDao: FavoriteDao
    ) : HomeDatabase = HomeRoomDatabase(appDatabase, bannerDao, goodDao, favoriteDao)

    @Singleton @Provides
    open fun provideDb(app : Application) : AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "homeapi.db").allowMainThreadQueries()
            .fallbackToDestructiveMigration().build()

    @Singleton @Provides
    fun provideBannerDao(db : AppDatabase) : BannerDao = db.bannerDao()

    @Singleton @Provides
    fun provideGoodDao(db : AppDatabase) : GoodDao = db.goodDao()

    @Singleton @Provides
    fun provideFavoriteDao(db : AppDatabase) : FavoriteDao = db.favoriteDao()

}