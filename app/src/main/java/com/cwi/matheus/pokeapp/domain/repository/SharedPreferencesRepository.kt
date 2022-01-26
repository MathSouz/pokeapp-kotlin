package com.cwi.matheus.pokeapp.domain.repository

interface SharedPreferencesRepository {

    fun writeBoolean(key: String, value: Boolean)
    fun writeFloat(key: String, value: Float)
    fun writeInt(key: String, value: Int)
    fun writeLong(key: String, value: Long)
    fun writeString(key: String, value: String)

    fun readBoolean(key: String, default: Boolean): Boolean
    fun readFloat(key: String, default: Float): Float
    fun readInt(key: String, default: Int): Int
    fun readLong(key: String, default: Long): Long
    fun readString(key: String, default: String): String?

    fun contains(key: String) : Boolean
}