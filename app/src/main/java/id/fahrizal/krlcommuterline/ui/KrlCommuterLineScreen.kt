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
import id.fahrizal.krlcommuterline.ui.route.find.FindScreen
import id.fahrizal.krlcommuterline.ui.route.find.FindRouteViewModel
import id.fahrizal.krlcommuterline.ui.station.detail.StationDetailScreen
import id.fahrizal.krlcommuterline.ui.station.find.FindStationScreen
import id.fahrizal.krlcommuterline.ui.station.find.FindStationViewModel

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
            AppBar(
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
                    onTxtDepartureClicked = {
                        navController.navigate(KrlCommuterLineScreen.FindStationDeparture.name)
                    },
                    onTxtDestinationClicked = {
                        navController.navigate(KrlCommuterLineScreen.FindStationDestination.name)
                    },
                    onStationClicked = {
                        navController.navigate(KrlCommuterLineScreen.StationDetail.name)
                    },
                    modifier = Modifier.fillMaxWidth().padding(innerPadding)
                )
            }

            composable(route = KrlCommuterLineScreen.FindStationDeparture.name) {
                FindStationScreen(
                    viewModel = findStationViewModel,
                    modifier = Modifier.fillMaxWidth().padding(innerPadding),
                    onSelected = { id,name ->
                        findRouteViewModel.setStationDeparture(id,name)
                        navController.navigateUp()
                    }
                )
            }

            composable(route = KrlCommuterLineScreen.FindStationDestination.name) {
                FindStationScreen(
                    viewModel = findStationViewModel,
                    modifier = Modifier.fillMaxWidth().padding(top = 62.dp),
                    onSelected = { id,name ->
                        findRouteViewModel.setStationDestination(id, name)
                        navController.navigateUp()
                    }
                )
            }

            composable(route = KrlCommuterLineScreen.StationDetail.name) {
                StationDetailScreen(
                    modifier = Modifier.fillMaxWidth().padding(top = 62.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
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
    FindStationDeparture(title = R.string.find_station),
    FindStationDestination(title = R.string.find_station),
    StationDetail(title = R.string.station_detail),
}
