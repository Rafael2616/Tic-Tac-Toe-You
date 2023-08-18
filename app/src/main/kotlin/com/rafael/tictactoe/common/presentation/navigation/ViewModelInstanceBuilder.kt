package com.rafael.tictactoe

import android.content.Context
import androix.compose.runtime.Composable
import androix.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmoodel.compose.viewModel
import androidx.navigation.NavBackEntryStackEntry
import androidx.navigation.NavController

@Composable
inline fun <reified T : ViewModel> NavBackEntryStackEntry.viewModelInstanceBuilder(
  context: Context,
  navController: NavController
): T {
  val navGraphRoute = dstination.parent?.route ?: return viewModel()
  val parentEntry = remember(context) {
    navController.getBackStackEntry(navGraphRoute)
  }
  return viewModel(parentEntry)
}
