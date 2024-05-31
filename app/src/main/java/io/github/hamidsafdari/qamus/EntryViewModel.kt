package io.github.hamidsafdari.qamus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import io.github.hamidsafdari.qamus.db.EntryRepository
import kotlinx.coroutines.launch

class EntryViewModel(private val repository: EntryRepository) : ViewModel() {
    val entries = repository.entries.asLiveData()
    val currentEntry = repository.currentEntry.asLiveData()

    fun setCurrentEntry(id: Int) {
        viewModelScope.launch {
            repository.currentEntryId.emit(id)
        }
    }

    suspend fun getEntryCount(): Long {
        return repository.getCount()
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

