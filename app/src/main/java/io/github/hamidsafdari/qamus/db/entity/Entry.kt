package io.github.hamidsafdari.qamus.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Entry(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "keyword") val keyword: String?,
    @ColumnInfo(name = "definition") val definition: String?
)