package io.github.hamidsafdari.qamus.db

import io.github.hamidsafdari.qamus.db.dao.EntryDao
import io.github.hamidsafdari.qamus.db.dto.MinEntryDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest

class EntryRepository(private val entryDao: EntryDao) {
    val entrySearchTerm = MutableStateFlow("")
    val currentEntryId = MutableStateFlow<Int?>(null)

    suspend fun getCount(): Long {
        return entryDao.count()
    }

    val entries: Flow<List<MinEntryDto>> = entryDao.findAllMin()

    @OptIn(ExperimentalCoroutinesApi::class)
    val currentEntry = currentEntryId.flatMapLatest { id ->
        if (id != null) entryDao.findById(id) else emptyFlow()
    }

}
