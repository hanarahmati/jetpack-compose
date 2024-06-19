package co.fanavari.mycomposeapplication.util

import android.os.Bundle
import android.os.Parcelable
import co.fanavari.mycomposeapplication.data.model.Task

fun List<Task>.toArrayList(): ArrayList<Task> {
    return ArrayList(this)
}

fun ArrayList<Parcelable>.toTaskList(): List<Task> {
    return this.filterIsInstance<Task>()
}