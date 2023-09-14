package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
<<<<<<< HEAD
=======
import androidx.compose.foundation.layout.fillMaxSize
>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
<<<<<<< HEAD
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
=======
>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.util.CardGridClickHandler
import com.rafael.tictactoe.game.domain.util.CardGridImageHandler
<<<<<<< HEAD
import com.rafael.tictactoe.game.domain.util.RandomCardSorter
=======
import com.rafael.tictactoe.game.domain.util.WinnerLineLogic
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinatesTest
>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import kotlinx.coroutines.launch

@Composable
<<<<<<< HEAD
fun CardGrid() {
=======
fun CardGrid(coordinates: WinnerLineCoordinatesTest) {
>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
    var cardItems by rememberSaveable { mutableStateOf(List<Boolean>(3){false})}
    var rowItems by rememberSaveable { mutableStateOf(List<Boolean>(3){false})}
    val viewModel = hiltViewModel<GameViewModel>()
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

<<<<<<< HEAD
    //TODO implement custom height for diferents screen sizes using window-size-class library
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            verticalArrangement = Arrangement.Bottom
=======
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
            verticalArrangement = Arrangement.Bottom,
>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
    ) {
        rowItems.forEachIndexed { rowIndex, _->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                cardItems.forEachIndexed { cardIndex, _ ->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(6.dp)
                            .clickable(onClick = {
<<<<<<< HEAD
=======
                                WinnerLineLogic(viewModel, coordinates);	
>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
				            	viewModel.updateIsExpandButtonShown(false)
                                scope.launch {
                                    CardGridClickHandler(context, viewModel, settingsVM, rowIndex, cardIndex)
                                };
                            }
                        )
                    ) {
                		Image(
					        contentDescription = null,
                            alignment = Alignment.Center,
<<<<<<< HEAD
 				           modifier = Modifier.width(114.dp).height(114.dp),
=======
 				           modifier = Modifier.height(114.dp).width(114.dp),
>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
 				           painter = CardGridImageHandler(rowIndex, cardIndex)
			        	)
                    }
                }
            }
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
