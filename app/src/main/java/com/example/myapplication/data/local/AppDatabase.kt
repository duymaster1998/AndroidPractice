package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.dao.MovieDao
import com.example.myapplication.data.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}