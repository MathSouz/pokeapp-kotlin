package com.cwi.matheus.pokeapp.data.sharedPreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.cwi.matheus.pokeapp.base.POKEAPP_PREFERENCES
import com.cwi.matheus.pokeapp.domain.repository.SharedPreferencesRepository

class SharedPreferencesRepositoryImpl(private val context: Context) : SharedPreferencesRepository {

    override fun writeBoolean(key: String, value: Boolean) {
        context.getSharedPreferences(POKEAPP_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .edit()
            .putBoolean(key, value)
            .apply()
    }

    override fun writeFloat(key: String, value: Float) {
        context.getSharedPreferences(POKEAPP_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .edit()
            .putFloat(key, value)
            .apply()
    }

    override fun writeInt(key: String, value: Int) {
        context.getSharedPreferences(POKEAPP_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .edit()
            .putInt(key, value)
            .apply()
    }

    override fun writeLong(key: String, value: Long) {
        context.getSharedPreferences(POKEAPP_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .edit()
            .putLong(key, value)
            .apply()
    }

    override fun writeString(key: String, value: String) {
        context.getSharedPreferences(POKEAPP_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .edit()
            .putString(key, value)
            .apply()
    }

    override fun readBoolean(key: String, default: Boolean): Boolean =
        context.getSharedPreferences(POKEAPP_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .getBoolean(key, default)

    override fun readFloat(key: String, default: Float): Float =
        context.getSharedPreferences(POKEAPP_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .getFloat(key, default)

    override fun readInt(key: String, default: Int): Int =
        context.getSharedPreferences(POKEAPP_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .getInt(key, default)

    override fun readLong(key: String, default: Long): Long =
        context.getSharedPreferences(POKEAPP_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .getLong(key, default)

    override fun readString(key: String, default: String): String? =
        context.getSharedPreferences(POKEAPP_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
            .getString(key, default)
}