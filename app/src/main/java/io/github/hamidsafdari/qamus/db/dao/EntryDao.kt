package io.github.hamidsafdari.qamus.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.github.hamidsafdari.qamus.db.entity.Entry

@Dao
interface EntryDao {
    @Query("SELECT COUNT(*) FROM Entry")
    suspend fun count(): Long

    @Query("SELECT * FROM Entry WHERE keyword LIKE :keyword ORDER BY keyword")
    fun findByKey(keyword: String): List<Entry>

    @Insert
    fun insertAll(vararg entries: Entry)

    @Delete
    fun delete(entry: Entry)
}