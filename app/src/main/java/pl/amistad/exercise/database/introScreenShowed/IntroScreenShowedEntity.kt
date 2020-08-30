package pl.amistad.exercise.database.introScreenShowed

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "intro_screen_showed_event")
class IntroScreenShowedEntity(
    @ColumnInfo(name = "timestamp") val timeStamp: Long,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)