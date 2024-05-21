package co.fanavari.mycomposeapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val name: String,
    val priority: Int,
    val isImportant: Boolean,
    val isCompleted: Boolean
):Parcelable
