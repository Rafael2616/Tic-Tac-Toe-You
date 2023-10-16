package com.rafael.tictactoe.game.presentation.game_screen.components.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.card_grid.CardGridClickHandler
import com.rafael.tictactoe.game.domain.card_grid.DrawXorCircle
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import kotlinx.coroutines.launch

@Composable
fun CardGrid() {
    val cardItems = remember { mutableStateListOf(0,1,2) }
    val rowItems = remember { mutableStateListOf(0,1,2) }
    val viewModel = hiltViewModel<GameViewModel>()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .padding(start = 8.dp, end = 8.dp, bottom = 45.dp),
        verticalArrangement = Arrangement.Bottom
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
                            .size(114.dp)
                            .clip(RoundedCornerShape(26.dp))
                            .clickable(onClick = {
				            	viewModel.showExpandButton(false)
                                scope.launch {
                                    CardGridClickHandler(
                                        context,
                                        viewModel,
                                        rowIndex,
                                        cardIndex
                                    )
                                };
                            })
                    ) {
                       Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.CenterHorizontally),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier.size(95.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                DrawXorCircle(
                                    rowIndex,
                                    cardIndex
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}