package io.github.hamidsafdari.qamus

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.hamidsafdari.qamus.ui.DefinitionScreen
import io.github.hamidsafdari.qamus.ui.EntryList
import io.github.hamidsafdari.qamus.ui.theme.QamusTheme
import kotlinx.coroutines.launch

enum class QamusScreen {
    EntryList,
    Definition
}

class MainActivity : ComponentActivity() {
    private val entryViewModel: EntryViewModel by viewModels {
        EntryViewModelFactory((application as QamusApp).repository)
    }

    override fun onStart() {
        super.onStart()

        lifecycleScope.launch {
            val count = entryViewModel.getEntryCount()
            Log.d("MainActivity", "entry count: $count")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val entries by entryViewModel.entries.observeAsState(emptyList())
            val currentEntry by entryViewModel.currentEntry.observeAsState()
            val navController = rememberNavController()
            val onEntryItemClicked: (Int) -> Unit = { id ->
                entryViewModel.setCurrentEntry(id)
                navController.navigate(QamusScreen.Definition.name)
            }


            QamusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = QamusScreen.EntryList.name
                    ) {
                        composable(QamusScreen.EntryList.name) {
                            EntryList(entries, onEntryItemClicked)
                        }
                        composable(QamusScreen.Definition.name) {
                            currentEntry?.let {
                                DefinitionScreen(it)
                            }
                        }
                    }
                }
            }
        }
    }
}
