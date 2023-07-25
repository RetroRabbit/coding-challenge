package com.example.gameofthrones.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gameofthrones.data.entities.HouseGameOfThrones
import com.example.gameofthrones.data.entities.StringListTypeConverter

@TypeConverters(StringListTypeConverter::class)
@Database(entities = [HouseGameOfThrones::class], version = 3, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun houseDao(): HouseGameOfThronesDao
    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "houses")
                .fallbackToDestructiveMigration()
                .build()
    }
}