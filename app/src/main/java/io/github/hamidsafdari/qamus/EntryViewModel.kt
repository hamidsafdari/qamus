package io.github.hamidsafdari.qamus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.hamidsafdari.qamus.db.EntryRepository

class EntryViewModel(private val entryRepository: EntryRepository) : ViewModel() {
    suspend fun getEntryCount(): Long {
        return entryRepository.getCount()
    }
}

class EntryViewModelFactory(private val repository: EntryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EntryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EntryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

