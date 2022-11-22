package com.example.win14.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.win14.model.FirstQuestions

@Database(entities = [FirstQuestions::class], version = 1)
@TypeConverters(RoomConverter::class)
abstract class AppDatabase: RoomDatabase(){
    abstract fun questionsDao(): QuestionsDao
    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null
        fun getDatabase(context: Context):AppDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}