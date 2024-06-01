package io.github.hamidsafdari.qamus.db

import android.text.TextUtils
import io.github.hamidsafdari.qamus.db.dao.EntryDao
import io.github.hamidsafdari.qamus.db.dto.MinEntryDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest

class EntryRepository(private val entryDao: EntryDao) {
    val entrySearchTerm = MutableStateFlow("")
    val currentEntryId = MutableStateFlow<Int?>(null)

    suspend fun getCount(): Long {
        return entryDao.count()
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val entries: Flow<List<MinEntryDto>> = entrySearchTerm
        .debounce(200)
        .flatMapLatest {
            if (TextUtils.isEmpty(it)) {
                entryDao.findAllMin()
            } else {
                entryDao.findAllMin(it)
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    val currentEntry = currentEntryId.flatMapLatest { id ->
        if (id != null) entryDao.findById(id) else emptyFlow()
    }

}
