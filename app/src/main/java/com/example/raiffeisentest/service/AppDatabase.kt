package com.example.raiffeisentest.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.raiffeisentest.interfaces.UserDao
import com.example.raiffeisentest.models.User
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val userEnteredPassphrase: CharArray = charArrayOf('p','a', 's', 's', 'w','o','r','d')
                val passphrase: ByteArray = SQLiteDatabase.getBytes(userEnteredPassphrase)
                val factory = SupportFactory(passphrase)
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .openHelperFactory(factory)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}