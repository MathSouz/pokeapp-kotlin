package com.cwi.matheus.pokeapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cwi.matheus.pokeapp.data.database.dao.PokemonDao
import com.cwi.matheus.pokeapp.data.database.entity.PokemonEntity
import com.cwi.matheus.pokeapp.data.database.typeconverter.Converters

@Database(entities = [PokemonEntity::class], version = 2)
@TypeConverters(Converters::class)
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