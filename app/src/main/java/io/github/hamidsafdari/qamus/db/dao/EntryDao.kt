package io.github.hamidsafdari.qamus.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.github.hamidsafdari.qamus.db.dto.MinEntryDto
import io.github.hamidsafdari.qamus.db.entity.Entry
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {
    @Query("SELECT * FROM Entry WHERE id = :id")
    fun findById(id: Int): Flow<Entry>

    @Query("SELECT * FROM Entry WHERE keyword LIKE :keyword ORDER BY keyword")
    fun searchByKeyword(keyword: String): List<Entry>

    @Query("SELECT * FROM Entry ORDER BY keyword")
    fun findAll(): Flow<List<Entry>>

    @Query("SELECT id, keyword, SUBSTRING(definition, 1, 300) as definition FROM Entry ORDER BY keyword")
    fun findAllMin(): Flow<List<MinEntryDto>>

    @Query(
        "SELECT id, keyword, SUBSTRING(definition, 1, 300) as definition " +
                "FROM Entry " +
                "WHERE keyword like '%' || :searchTerm || '%' " +
                "ORDER BY keyword"
    )
    fun findAllMin(searchTerm: String): Flow<List<MinEntryDto>>

    @Query("SELECT COUNT(*) FROM Entry")
    suspend fun count(): Long

    @Insert
    fun insertAll(vararg entries: Entry)

    @Delete
    fun delete(entry: Entry)
}