package com.sedakarana.posedetection.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.sedakarana.navigation.navigation.Screen
import com.sedakarana.posedetection.view.screen.HomeScreen
import com.sedakarana.posedetection.view.screen.InfoScreen
import com.sedakarana.posedetection.viewModel.HomeViewModel

@SuppressLint("UnsafeOptInUsageError")
@Composable
@ExperimentalPermissionsApi

fun MainNavGraph(navController: NavHostController) { //Ekran geçişlerini kontrol etmemizi sağlar.NavHostController erkanlar arası geçiş yapmamızı kontrol eden parametredir.
    val homeViewModel: HomeViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController, viewModel = homeViewModel)
        }

        composable(route = Screen.InfoScreen.route) {
            InfoScreen(navController = navController, viewModel = homeViewModel)
        }


    }
}