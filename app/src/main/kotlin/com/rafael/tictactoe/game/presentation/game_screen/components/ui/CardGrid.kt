package com.rafael.tictactoe.game.presentation.game_screen.components.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
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
    val scope = rememberCoroutineScope()
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = (if (orientation == Configuration.ORIENTATION_LANDSCAPE) true else false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = if (isInLandscape) 50.dp else 8.dp, bottom = if (isInLandscape) 0.dp else 50.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = if (isInLandscape) Alignment.End else Alignment.CenterHorizontally
    ) {
        rowItems.forEachIndexed { rowIndex, _->
            Row(
                modifier = Modifier
                    .fillMaxWidth(if (isInLandscape) 0.52f else 1f)
                    .padding(2.dp)
            ) {
                cardItems.forEachIndexed { cardIndex, _ ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(15.dp)
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(6.dp)
                            .size(114.dp)
                            .clip(RoundedCornerShape(26.dp))
                            .clickable(onClick = {
				            	viewModel.showExpandButton(false)
                                scope.launch {
                                    CardGridClickHandler(
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