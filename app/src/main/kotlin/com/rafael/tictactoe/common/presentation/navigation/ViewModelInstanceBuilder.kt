package com.rafael.tictactoe

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.viewModelInstanceBuilder(
  context: Context,
  navController: NavController
): T {
  val navGraphRoute = destination.parent?.route ?: return viewModel()
  val parentEntry = remember(context) {
    navController.getBackStackEntry(navGraphRoute)
  }
  return viewModel(parentEntry)
}
