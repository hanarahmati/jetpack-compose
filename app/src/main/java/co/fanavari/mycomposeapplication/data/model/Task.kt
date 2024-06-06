package co.fanavari.mycomposeapplication.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey val id: String,
    val name: String,
    val priority: Int,
    val isImportant: Boolean,
    val isCompleted: Boolean
)
