package com.cwi.matheus.pokeapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cwi.matheus.pokeapp.data.database.dao.PokemonDao
import com.cwi.matheus.pokeapp.data.database.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao

    companion object {
        private const val DATABASE_NAME = "pokeapp-db"
        fun create(application: Application): AppDatabase {
            return Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}