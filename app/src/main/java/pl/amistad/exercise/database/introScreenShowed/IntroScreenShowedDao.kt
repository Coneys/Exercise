package pl.amistad.exercise.database.introScreenShowed

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IntroScreenShowedDao {
    @Insert
    fun insert(entity: IntroScreenShowedEntity)

    @Query("SELECT * FROM intro_screen_showed_event")
    fun getAll(): List<IntroScreenShowedEntity>
}