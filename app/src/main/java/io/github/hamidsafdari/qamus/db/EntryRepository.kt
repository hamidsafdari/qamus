package io.github.hamidsafdari.qamus.db

import io.github.hamidsafdari.qamus.db.dao.EntryDao
import io.github.hamidsafdari.qamus.db.dto.MinEntryDto
import io.github.hamidsafdari.qamus.db.entity.Entry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class EntryRepository(private val entryDao: EntryDao) {
    val entrySearchTerm = MutableStateFlow("")

    suspend fun getCount(): Long {
        return entryDao.count()
    }

    val entries: Flow<List<MinEntryDto>> = entryDao.findAllMin()

}
