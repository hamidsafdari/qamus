package io.github.hamidsafdari.qamus

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import io.github.hamidsafdari.qamus.ui.theme.QamusTheme
import kotlinx.coroutines.launch

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
            QamusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QamusTheme {
        Greeting("Android")
    }
}