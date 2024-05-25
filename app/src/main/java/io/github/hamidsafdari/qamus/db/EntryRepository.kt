package io.github.hamidsafdari.qamus.db

import io.github.hamidsafdari.qamus.db.dao.EntryDao
import kotlinx.coroutines.flow.MutableStateFlow

class EntryRepository(private val entryDao: EntryDao) {
    val entrySearchTerm = MutableStateFlow("")

    suspend fun getCount(): Long {
        return entryDao.count()
    }
}
