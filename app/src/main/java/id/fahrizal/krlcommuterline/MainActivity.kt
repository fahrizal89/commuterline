package id.fahrizal.krlcommuterline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import id.fahrizal.krlcommuterline.presentation.find.FindScreen
import id.fahrizal.krlcommuterline.presentation.find.FindViewModel
import id.fahrizal.krlcommuterline.ui.theme.KrlcommuterlineTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val findViewModel : FindViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KrlcommuterlineTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FindScreen(findViewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
//    FindScreen()
}