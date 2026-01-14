package org.coffeebreak.data.data_source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.coffeebreak.data.data_source.local.dao.UserDao
import org.coffeebreak.data.dto.UserModelDto

@Database(entities = [UserModelDto::class], version = 1)
abstract class AppDatabase(): RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        fun createDatabase(context: Context): AppDatabase {
            val applicationContext = context.applicationContext
            val db = applicationContext.getDatabasePath("coffeebreak.db")

            return Room.databaseBuilder(
                context, AppDatabase::class.java, "coffeebreak.db"
            ).build()

        }
    }
}