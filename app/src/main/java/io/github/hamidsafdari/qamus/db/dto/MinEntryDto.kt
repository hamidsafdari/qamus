package io.github.hamidsafdari.qamus.db.dto

import androidx.compose.runtime.Immutable

@Immutable
data class MinEntryDto(
    val id: Int,
    val keyword: String,
    val definition: String
)
