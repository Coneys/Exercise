package pl.amistad.exercise.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.amistad.exercise.App
import pl.amistad.exercise.database.introScreenShowed.IntroScreenShowedDao
import pl.amistad.exercise.database.introScreenShowed.IntroScreenShowedEntity

@Database(entities = arrayOf(IntroScreenShowedEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun introShowedDao(): IntroScreenShowedDao
}

object Database{
    val db = Room.databaseBuilder(
        App.context,
        AppDatabase::class.java, "database-name"
    )
        .allowMainThreadQueries()
        .build()
}