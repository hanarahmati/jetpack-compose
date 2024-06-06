package co.fanavari.mycomposeapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import co.fanavari.mycomposeapplication.data.dao.TaskDao
import co.fanavari.mycomposeapplication.data.model.Task


@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}