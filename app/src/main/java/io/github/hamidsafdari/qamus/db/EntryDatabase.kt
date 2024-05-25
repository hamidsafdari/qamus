package io.github.hamidsafdari.qamus.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.hamidsafdari.qamus.db.dao.EntryDao
import io.github.hamidsafdari.qamus.db.entity.Entry
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Entry::class], version = 1)
abstract class EntryDatabase : RoomDatabase() {
    abstract fun entryDao(): EntryDao

    companion object {
        private var INSTANCE: EntryDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): EntryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EntryDatabase::class.java,
                    "qamus.db"
                ).createFromAsset("book.db").build()
                INSTANCE = instance
                instance
            }
        }
    }
}