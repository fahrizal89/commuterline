package id.fahrizal.krlcommuterline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import id.fahrizal.krlcommuterline.domain.model.StationCard
import id.fahrizal.krlcommuterline.domain.model.StationCardBranch
import id.fahrizal.krlcommuterline.domain.model.StepCardState
import id.fahrizal.krlcommuterline.ui.KrlCommuterLineApp
import id.fahrizal.krlcommuterline.ui.guide.GuideWidget
import id.fahrizal.krlcommuterline.ui.theme.KrlcommuterlineTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KrlcommuterlineTheme {
                KrlCommuterLineApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    val stops = ArrayList<StationCard>().apply {
        add(
            StationCard(0, 0,"Karet", next = StationCardBranch(1, ArrayList<String>().apply { add("2") }), state = StepCardState.START)
        )

        add(
            StationCard(1, 1,"Tanah Abang", next = StationCardBranch(0, ArrayList<String>().apply { add("3") }), state = StepCardState.TRANSIT)
        )

        add(
            StationCard(2, 2,"Palmerah",next = StationCardBranch(1, ArrayList<String>().apply { add("3") }), state = StepCardState.STRAIGHT, groupIndex = 1)
        )

        add(
            StationCard(3, 3,"Kebayoran",next = StationCardBranch(1, ArrayList<String>().apply { add("3") }), state = StepCardState.END, groupIndex = 1)
        )
    }
    GuideWidget(stops)
}