package id.fahrizal.krlcommuterline.ui

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.fahrizal.krlcommuterline.R
import id.fahrizal.krlcommuterline.ui.find.route.FindScreen
import id.fahrizal.krlcommuterline.ui.find.route.FindRouteViewModel
import id.fahrizal.krlcommuterline.ui.find.station.FindStationScreen
import id.fahrizal.krlcommuterline.ui.find.station.FindStationViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KrlCommuterLineApp(
    findRouteViewModel: FindRouteViewModel = viewModel(),
    findStationViewModel: FindStationViewModel = viewModel(),
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = KrlCommuterLineScreen.valueOf(
        backStackEntry?.destination?.route ?: KrlCommuterLineScreen.FindRouteLine.name
    )

    Scaffold(
        topBar = {
            LunchTrayAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = KrlCommuterLineScreen.FindRouteLine.name,
        ) {
            composable(route = KrlCommuterLineScreen.FindRouteLine.name) {
                FindScreen(
                    viewModel = findRouteViewModel,
                    onTxtFromClicked = {
                        navController.navigate(KrlCommuterLineScreen.FindStationFrom.name)
                    },
                    onTxtToClicked = {
                        navController.navigate(KrlCommuterLineScreen.FindStationTo.name)
                    },
                    modifier = Modifier.fillMaxWidth().padding(innerPadding)
                )
            }

            composable(route = KrlCommuterLineScreen.FindStationFrom.name) {
                FindStationScreen(
                    viewModel = findStationViewModel,
                    modifier = Modifier.fillMaxWidth().padding(innerPadding),
                    onSelected = { id ->
                        findRouteViewModel.setStationFrom(id)
                        navController.navigateUp()
                    }
                )
            }

            composable(route = KrlCommuterLineScreen.FindStationTo.name) {
                FindStationScreen(
                    viewModel = findStationViewModel,
                    modifier = Modifier.fillMaxWidth().padding(top = 62.dp),
                    onSelected = { id ->
                        findRouteViewModel.setStationTo(id)
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(currentScreenTitle)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

enum class KrlCommuterLineScreen(@StringRes val title: Int) {
    FindRouteLine(title = R.string.app_name),
    FindStationFrom(title = R.string.find_station),
    FindStationTo(title = R.string.find_station),
}
