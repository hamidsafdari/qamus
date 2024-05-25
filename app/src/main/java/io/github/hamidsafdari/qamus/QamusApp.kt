package io.github.hamidsafdari.qamus

import android.app.Application
import io.github.hamidsafdari.qamus.db.EntryDatabase
import io.github.hamidsafdari.qamus.db.EntryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class QamusApp : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { EntryDatabase.getDatabase(this, applicationScope) }
    val repository by lazy {
        EntryRepository(entryDao = database.entryDao())
    }
}