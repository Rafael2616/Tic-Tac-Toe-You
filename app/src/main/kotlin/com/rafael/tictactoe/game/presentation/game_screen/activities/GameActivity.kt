package com.rafael.tictactoe.game.presentation.game_screen.activities

import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.presentation.ui.theme.AppTheme
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.game.presentation.main_screen.activities.MainActivity
import android.content.*
import android.media.MediaPlayer
import android.os.Bundle
//import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.*
//import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.*
import androidx.compose.runtime.livedata.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.graphics.painter.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.*
import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import android.net.Uri
import dagger.hilt.android.AndroidEntryPoint
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@AndroidEntryPoint
class GameActivity : ComponentActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
            setContent{ AppTheme(this) { gameScreen()
        } 
    }
}

  override fun onStop() {
   stopService(Intent(this, MusicService::class.java))
     super.onStop()
}
  override fun onResume() {
   startService(Intent(this, MusicService::class.java))
     super.onResume()
}

@Composable
fun gameScreen() {
 
 // val viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
  val xImage = painterResource(R.drawable.x_image)
  val circleImage = painterResource(R.drawable.circle_image)
  val xImageMax = painterResource(R.drawable.x_image_max_size)
  val circleImageMax = painterResource(R.drawable.circle_image_max_size)
  val restartIcon = painterResource(R.drawable.reset_icon)
  val transparentImage = painterResource(R.drawable.transparent_image)

  val genericPlayerName = stringResource(R.string.player)
  val genericPlayerX = stringResource(R.string.player_x)
  val genericPlayerO = stringResource(R.string.player_o)
  val gotOldString = stringResource(R.string.got_old)

  val clickSound1 = remember { MediaPlayer.create(this, R.raw.click1_sound) }
  val clickSound2 = remember { MediaPlayer.create(this, R.raw.click2_sound) }
  val victorySound = remember { MediaPlayer.create(this, R.raw.winner_victory_sound) }
  val gotOldSound = remember { MediaPlayer.create(this, R.raw.got_old_sound) }
  val uiSound = remember { MediaPlayer.create(this, R.raw.ui_click_sound) }

  val sharedPreferences = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
  val editor = sharedPreferences.edit()
  var isMusicOn = sharedPreferences.getBoolean("isBackgroundMusicOn", true)
  var isSoundOn = sharedPreferences.getBoolean("isSoundEffectsOn", true)
  
  var player1Name by rememberSaveable { mutableStateOf("") }
  var player2Name by rememberSaveable { mutableStateOf("") }

  var player1Wins by rememberSaveable { mutableStateOf(0) }
  var player2Wins by rememberSaveable { mutableStateOf(0) }

  var cards by rememberSaveable { mutableStateOf(List<Boolean>(9){false}) }
  var isCardClicked by rememberSaveable { mutableStateOf(List<Boolean>(9){false}) }
  
  var isScoreUp by rememberSaveable { mutableStateOf(false) }
  var isXImage by rememberSaveable { mutableStateOf(false) }
  var is1Win by rememberSaveable { mutableStateOf(false) }
  var isXFor1 by rememberSaveable { mutableStateOf(false) }
  var isPlayer1 by rememberSaveable { mutableStateOf(true) }
  var player1InToast by rememberSaveable { mutableStateOf(true) }
  var play by rememberSaveable { mutableStateOf(false) }
  var expandButton by rememberSaveable { mutableStateOf(false) }
  var isWinSoundPlaying by rememberSaveable { mutableStateOf(false) }
  var isGotOldSoundPlaying by rememberSaveable { mutableStateOf(false) }
  var isSoundEffectsOn  by rememberSaveable { mutableStateOf(isSoundOn) }
  var isBackgroundMusicOn by rememberSaveable { mutableStateOf(isMusicOn) }

  var startX by rememberSaveable { mutableStateOf(0f)}
  var startY by rememberSaveable { mutableStateOf(0f)}
  var endX by rememberSaveable { mutableStateOf(0f)}
  var endY by rememberSaveable { mutableStateOf(0f)}


fun uiClickSound() {
    if (isSoundEffectsOn) {
        uiSound.start()
    } else {
        uiSound.stop()
    }    
}


fun setPlayerImagePref(){

if (!play){
    if (isXFor1){
        isXImage = true
    } else isXImage = false
  }
}

Surface(
      color = MaterialTheme.colorScheme.surface,
     // modifier = Modifier.fillMaxSize()
 ) {


@Composable
fun getPlayerPrefs() {

val maxLength = 10
var player1Value = if (player1InToast) player1Name else player2Name
var player2Value = if (player1InToast) player2Name else player1Name
    
      Box(
          modifier = Modifier
         .fillMaxSize()
   ) {
        Card(
            shape = MaterialTheme.shapes.large,
            border = BorderStroke(1.5.dp, Color.Gray),
            modifier = Modifier               
           .width(380.dp)
           .height(240.dp)
           .align(Alignment.Center)
       ) {
            Row(
                modifier = Modifier
               .fillMaxWidth()
               .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
          ) {
                TextField(
                      value = player1Value,
                      onValueChange = { if (it.length <= maxLength) {if (isPlayer1) player1Name = it
                      else player2Name = it} },
                      label = { Text( stringResource(id = R.string.player1_name)) },
                      placeholder = { Text( stringResource(id = if (isXFor1) R.string.player_x
                      else R.string.player_o)) },
                      modifier = Modifier
                     .weight(1f)
                      )
                      Card(
                           shape = MaterialTheme.shapes.large,
                           modifier = Modifier
                          .width(60.dp)
                          .height(85.dp)
                          .clickable(onClick = {uiClickSound(); setPlayerImagePref(); isPlayer1 = !isPlayer1})
                       ) {
                Image(
                     contentDescription = null,
                     modifier = Modifier.fillMaxSize(),
                     painter = painterResource(R.drawable.switch_icon)
                   )    
               }
  TextField(
            value = player2Value,
            onValueChange = { if (it.length <= maxLength) { if (isPlayer1) player2Name = it
            else player1Name = it}},              
            label = { Text( stringResource(id = R.string.player2_name)) },
            placeholder = { Text( stringResource(id = if (isXFor1) R.string.player_o
            else R.string.player_x)) },
            modifier = Modifier
           .weight(1f)
      )
}

Row(
     modifier = Modifier
    .fillMaxWidth(),
     horizontalArrangement = Arrangement.SpaceBetween
 ) {  
      Image(
            contentDescription = null,
            painter = (if (isXFor1) xImage else circleImage),
            modifier = Modifier
           .width(120.dp)
           .height(60.dp)                         
            )  
            Card(
                 shape = MaterialTheme.shapes.large,
                 modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .clickable(onClick = {uiClickSound(); isXFor1 = !isXFor1; setPlayerImagePref() })   
            ) {         
                 Image(
                      contentDescription = null,
                      modifier = Modifier.fillMaxSize(),
                      painter = painterResource(R.drawable.switch_icon)
                     )    
                 }
       Image(
             contentDescription = null,
             painter = (if (isXFor1) circleImage else xImage),            
             modifier = Modifier
            .width(120.dp)
            .height(60.dp)
      )
}

Button(
       onClick = { uiClickSound(); play = true },
       modifier = Modifier
      .width(100.dp).height(45.dp)
      .align(Alignment.CenterHorizontally),          
   ) {
         Box(
             modifier = Modifier
            .fillMaxHeight(),
             contentAlignment = Alignment.Center      
       ) {
           Row(
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.Center,
               verticalAlignment = Alignment.CenterVertically,  
           ) {
               Text(
                   text = stringResource(id = R.string.play),
                   style = MaterialTheme.typography.bodyLarge,                            
                  )       
               }
            }
         }
      }
   }
}
/*
Canvas(modifier = Modifier.fillMaxSize()) {
    val canvasWidth = size.width
    val canvasHeight = size.height /2.12F
    val canvasMaxHeight = size.height
 
    drawLine(
        start = Offset(x = 6.dp.toPx(), y = canvasHeight),
        end = Offset(x = canvasWidth - 6.dp.toPx(), canvasMaxHeight - 18.dp.toPx()),
        color = Color.Yellow,
        strokeWidth = 20.dp.toPx(),
    )
}
*/


fun setFirstHorizontalLinePositions(){
startX = 15f
startY = 725f
endX = 705f
endY = 725f
}

fun setSecondHorizontalLinePositions(){
startX = 15f
startY = 950f
endX = 705f
endY = 950f
}

fun setTertiaryHorizontalLinePositions(){
startX = 15f
startY = 1170f
endX = 705f
endY = 1170f
}

fun setFirstVerticalLinePositions(){
startX = 125f
startY = 610f
endX = 125f
endY = 1285f
}

fun setSecondVerticalLinePositions(){
startX = 362f
startY = 610f
endX = 362f
endY = 1285f
}

fun setTertiaryVerticalLinePositions(){
startX = 595f
startY = 610f
endX = 595f
endY = 1285f
}

fun setStartToEndTransversalLinePositions(){
startX = 15f
startY = 620f
endX = 705f
endY = 1275f
}

fun setEndToStartTransversalLinePositions(){
startX = 705f
startY = 620f
endX = 15f
endY = 1275f
}


fun drawWinnerLineLogic() {

if (isCardClicked[0] && isCardClicked[1] && isCardClicked[2]){
    if ((cards[0] && cards[1] && cards[2]) || (!cards[0] && !cards[1] && !cards[2])) {
           setFirstHorizontalLinePositions()
   } 
}
if (isCardClicked[3] && isCardClicked[4] && isCardClicked[5]){
    if ((cards[3] && cards[4] && cards[5]) || (!cards[3] && !cards[4] && !cards[5])) {
           setSecondHorizontalLinePositions()
   } 
}
if (isCardClicked[6] && isCardClicked[7] && isCardClicked[8]){  
    if ((cards[6] && cards[7] && cards[8]) || (!cards[6] && !cards[7] && !cards[8])) {
           setTertiaryHorizontalLinePositions()
   }
}
if (isCardClicked[0] && isCardClicked[3] && isCardClicked[6]){
    if ((cards[0] && cards[3] && cards[6]) || (!cards[0] && !cards[3] && !cards[6])) {
           setFirstVerticalLinePositions()
   }
}
if (isCardClicked[1] && isCardClicked[4] && isCardClicked[7]){ 
    if ((cards[1] && cards[4] && cards[7]) || (!cards[1] && !cards[4] && !cards[7])) {
           setSecondVerticalLinePositions()
   }
}
if (isCardClicked[2] && isCardClicked[5] && isCardClicked[8]){
    if ((cards[2] && cards[5] && cards[8]) || (!cards[2] && !cards[5] && !cards[8])) {
           setTertiaryVerticalLinePositions()
   }
}
if (isCardClicked[0] && isCardClicked[4] && isCardClicked[8]){
    if ((cards[0] && cards[4] && cards[8]) || (!cards[0] && !cards[4] && !cards[8])) {
           setStartToEndTransversalLinePositions()
   }
}
if (isCardClicked[2] && isCardClicked[4] && isCardClicked[6]){
    if ((cards[2] && cards[4] && cards[6]) || (!cards[2] && !cards[4] && !cards[6])) {
           setEndToStartTransversalLinePositions()
      }
   } 
}


@Composable
fun drawWinnerLine() {

 Column(
      modifier = Modifier
     .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally
 ) {
     Canvas(
           modifier = Modifier
          .fillMaxSize()
          .weight(1f)
    ) {
         drawLine(
              color = Color.Yellow,
              strokeWidth = 20.dp.toPx(),
              start = Offset(x = startX, y = startY),
              end = Offset(x = endX, y = endY)
            )
        }          
    }
}


fun checkWinner(): Boolean {
    val winningPositions = listOf(
        listOf(0, 1, 2),
        listOf(3, 4, 5),
        listOf(6, 7, 8),
        listOf(0, 3, 6),
        listOf(1, 4, 7),
        listOf(2, 5, 8),
        listOf(0, 4, 8),
        listOf(2, 4, 6)
   )
   for (positions in winningPositions) {
         if (positions.all { (isCardClicked.getOrNull(it) == true) &&
                     (cards.getOrNull(it) == false )}) {
         is1Win = false
         return true
       }
       else {
         if (positions.all { (isCardClicked.getOrNull(it) == true) && 
                    (cards.getOrNull(it) == true )}) {
         is1Win = true
         return true
         }
      }
   }
   return false
}
val winner = checkWinner()


fun gotOld(): Boolean{

  if ((isCardClicked.all { it }) && (!winner)) {
  return true
  }
   return false
}
var gotOld = gotOld()


fun handleCardClick(index: Int) {

if ((play) && (!winner)) {

     if (!isCardClicked[index]) {
          if (isXImage){
             isCardClicked = isCardClicked.toMutableList().also {
               it[index] = true
               }
               cards = cards.toMutableList().also {
             it[index] = true
            }
        if (isSoundEffectsOn) {
         clickSound1.start()
           }
           isXImage = false
         isPlayer1 = !isPlayer1
           }     
       else {
              isCardClicked = isCardClicked.toMutableList().also {
                it[index] = true
                }
               cards = cards.toMutableList().also {
             it[index] = false
            }
          if (isSoundEffectsOn) {
            clickSound2.start()
            }
           isXImage = true
           isPlayer1 = !isPlayer1
        }
    }
}
}


fun setCardImage(index: Int): Painter {
    val cardImage = if (isCardClicked[index]){
        if (cards[index]) xImage else circleImage
    } else {
        transparentImage
    }
    return cardImage
}


@Composable
fun cardGrid(){

  ConstraintLayout {
     val cardNumber = List(cards.size) { createRef() }
     
     val top1 = createGuidelineFromTop(0.55f)
     val top2 = createGuidelineFromTop(0.68f)
     val top3 = createGuidelineFromTop(0.81f)
     val bottom1 = createGuidelineFromBottom(0.44f)
     val bottom2 = createGuidelineFromBottom(0.23f)
     val bottom3 = createGuidelineFromBottom(0.02f)
     val start1 = createGuidelineFromStart(0.05f)
     val start2 = createGuidelineFromStart(0.4f)
     val start3 = createGuidelineFromStart(0.7f)
     val end1 = createGuidelineFromEnd(0.7f)
     val end2 = createGuidelineFromEnd(0.4f)
     val end3 = createGuidelineFromEnd(0.05f)

cards.forEachIndexed { index, _ ->
   val cardRef = cardNumber[index]

 Card(     
     shape = MaterialTheme.shapes.medium,
      modifier = Modifier
        .width(110.dp)
        .height(110.dp)
        .clickable(onClick = { handleCardClick(index); drawWinnerLineLogic(); expandButton = false})
        .constrainAs(cardRef) {
      when (index) {
    0 -> {
         top.linkTo(top1)
         bottom.linkTo(bottom1); (cardNumber[3].top)
         start.linkTo(start1)
         end.linkTo(end1); (cardNumber[1].start)
         }
   1 -> {
         top.linkTo(top1)
         bottom.linkTo(bottom1); (cardNumber[4].top)
         start.linkTo(start2); (cardNumber[0].end)
         end.linkTo(end2); (cardNumber[2].start)
         }
   2 -> {
         top.linkTo(top1)
         bottom.linkTo(bottom1); (cardNumber[5].top)
         start.linkTo(start3); (cardNumber[1].end)
         end.linkTo(end3)
         }
   3 -> {
         top.linkTo(top2); (cardNumber[0].bottom)
         bottom.linkTo(bottom2); (cardNumber[6].top)
         start.linkTo(start1)
         end.linkTo(end1); (cardNumber[4].start)
         }
   4 -> {
         top.linkTo(top2); (cardNumber[1].bottom)
         bottom.linkTo(bottom2); (cardNumber[7].top)
         start.linkTo(start2); (cardNumber[3].end)
         end.linkTo(end2); (cardNumber[5].start)
         }
   5 -> {
         top.linkTo(top2); (cardNumber[2].bottom)
         bottom.linkTo(bottom2); (cardNumber[8].top)
         start.linkTo(start3); (cardNumber[4].end)
         end.linkTo(end3)
         }
   6 -> {
         top.linkTo(top3); (cardNumber[3].bottom)
         bottom.linkTo(bottom3)
         start.linkTo(start1)
         end.linkTo(end1); (cardNumber[7].start)
         }
   7 -> {
         top.linkTo(top3); (cardNumber[4].bottom)
         bottom.linkTo(bottom3) 
         start.linkTo(start2); (cardNumber[6].end)
         end.linkTo(end2); (cardNumber[8].start)
         }
   8 -> {
         top.linkTo(top3); (cardNumber[5].bottom)
         bottom.linkTo(bottom3)
         start.linkTo(start3); (cardNumber[7].end)
         end.linkTo(end3)
         }
  else ->  error ("Inválid Index")
               }
             }
           )
         {
   Image(
         contentDescription = null,
         modifier = Modifier.fillMaxSize(),
         painter = setCardImage(index)
        )
      }
    }
  }
}


@Composable
fun gameStateToastLogic(): String {
var playerName = if(!winner) {
if  (isPlayer1) player1Name else player2Name
}
else { if (!isPlayer1) player1Name else player2Name
}

if (gotOld) {
    return gotOldString
}

return if ((player1Name.isEmpty()) && (player2Name.isEmpty()))
 genericPlayerName
else if ((player1Name.isNotBlank()) && (player2Name.isNotBlank()))
 playerName
else if ((player1Name.isEmpty()) && (player2Name.isNotBlank()) && (isPlayer1))
 genericPlayerName
else if ((player1Name.isEmpty()) && (player2Name.isNotBlank()) && (!isPlayer1))
 player2Name
else if ((player1Name.isNotBlank()) && (player2Name.isEmpty()) && (isPlayer1))
 player1Name
else if ((player1Name.isNotBlank()) && (player2Name.isEmpty()) && (!isPlayer1))
 genericPlayerName
else ("")
}


fun toastImageLogic(): Painter {

return if (!winner) {
    if (isXImage) xImageMax else circleImageMax
}else {
    if(!isXImage) xImageMax else circleImageMax
   }         
}


@Composable
fun gameStateToast(){

Box(
    modifier = Modifier
   .fillMaxWidth(),
    contentAlignment = Alignment.TopCenter
 ) {
      Column(
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier
          .padding(top = 140.dp)
           ) {
               Box(
                   modifier = Modifier
                  .fillMaxWidth(),
                   contentAlignment = Alignment.Center      
               ) {
             Row(
                modifier = Modifier
               .wrapContentSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
           ) {
                Text(
                    text = (gameStateToastLogic()),
                    style = MaterialTheme.typography.titleMedium,
                    color = if(!gotOld) MaterialTheme.colorScheme.onSurface
                    else MaterialTheme.colorScheme.error,
                   )          
  if (!gotOld) {
             Spacer(
                    modifier = Modifier.padding(3.dp)
                    )
              Image(
                    contentDescription = null,
                    painter = (toastImageLogic()),
                    modifier = Modifier
                   .height(17.dp)   
                   .width(17.dp)  
                  )
               }
  if(winner){
             Spacer(
                    modifier = Modifier
                    .padding(3.dp)
                    )
                 Text(
                    text = stringResource(R.string.winner),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Green
                  )
               } 
            }
         }
      }
   }
}


fun displayFirstPlayerNameLogic(): String {

if(!play) player1InToast = isPlayer1
  
     return   if (isXFor1) {
            if (player1InToast) {
                if (player1Name.isEmpty()) {
                    genericPlayerX
                } else {
                    player1Name
                }
            } else {
                if (player2Name.isEmpty()) {
                    genericPlayerX
                } else {
                    player2Name
                }
            }
        } else {
            if (player1InToast) {
                if (player2Name.isEmpty()) {
                    genericPlayerX
                } else {
                    player2Name
                }
            } else {
                if (player1Name.isEmpty()) {
                    genericPlayerX
                } else {
                    player1Name
               }
          }
     }
}


fun displaySecondPlayerNameLogic(): String {

       return if (isXFor1) {
            if (player1InToast) {
                if (player2Name.isEmpty()) {
                    genericPlayerO
                } else {
                    player2Name
                }
            } else {
                if (player1Name.isEmpty()) {
                    genericPlayerO
                } else {
                    player1Name
                }
            }
        } else {
            if (player1InToast) {
                if (player1Name.isEmpty()) {
                    genericPlayerO
                } else {
                    player1Name
                }
            } else {
                if (player2Name.isEmpty()) {
                    genericPlayerO
                } else {
                    player2Name
               }
          }
     }
} 


@Composable
fun displayPlayerNames(){

 Box(
     modifier = Modifier
     .fillMaxWidth(),
     contentAlignment = (Alignment.TopCenter)
  ) {
      Column(
             verticalArrangement = Arrangement.Center,
             modifier = Modifier
            .wrapContentSize()
            .padding(top = 24.dp, end = 80.dp)
         ) {
            Text(
                 text =  displayFirstPlayerNameLogic(),
                 style = MaterialTheme.typography.bodyMedium,
                 color = MaterialTheme.colorScheme.onSurface,                 
               )
        }
 }
 Box(
     modifier = Modifier
    .fillMaxWidth(),
     contentAlignment = (Alignment.TopCenter)
   ) {
      Column(
             verticalArrangement = Arrangement.Center,
             modifier = Modifier
            .wrapContentSize()
            .padding(top = 24.dp, start = 80.dp)
          ) {
             Text(
                  text = displaySecondPlayerNameLogic(),
                  style = MaterialTheme.typography.bodyMedium,
                  color = MaterialTheme.colorScheme.onSurface,
                  )
            }
      }
}


fun victoryCountLogic(){
checkWinner()
if (!isScoreUp) {
    if (winner) {isScoreUp = true;
        if (is1Win) {
           player1Wins += 1
        } else {
           player2Wins += 1
         }
      }
   }
}


@Composable
fun victoryCount(){

victoryCountLogic()

  Box(
      modifier = Modifier
      .fillMaxWidth(),
      contentAlignment = (Alignment.TopCenter)
  ) {
      Column(
            verticalArrangement = Arrangement.Center,
             modifier = Modifier
            .wrapContentSize()
            .padding(top = 55.dp)
     ) {         
           Row(
              modifier = Modifier
             .wrapContentSize(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically            
       ) {
      Card(
           shape = MaterialTheme.shapes.medium,
           modifier = Modifier
          .width(65.dp)
          .height(55.dp)
       ) {
             Text(
                  text = "$player1Wins",
                  style = MaterialTheme.typography.titleLarge,
                  color = MaterialTheme.colorScheme.onSurface,
                  modifier = Modifier
                 .align(Alignment.CenterHorizontally)
                 )
           Image(
                 contentDescription = null,
                 modifier = Modifier.fillMaxSize(),
                 painter = painterResource(R.drawable.x_image)
                 )      
               }
       Spacer(
              modifier = Modifier
             .padding(6.dp)
             )            
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
           .width(65.dp)
           .height(55.dp)
        ) {
             Text(
                  text = "$player2Wins",
                  style = MaterialTheme.typography.titleLarge,
                  color = MaterialTheme.colorScheme.onSurface,
                  modifier = Modifier
                 .align(Alignment.CenterHorizontally)
                  )
            Image(
                  contentDescription = null,
                  modifier = Modifier.fillMaxSize(),
                  painter = painterResource(R.drawable.circle_image)
                  )    
               }
           }    
       }
   }
}


fun resetVictoryCountLogic(){

player1Wins = 0; player2Wins = 0
}

@Composable
fun resetVictoryCount(){

Box(
    modifier = Modifier
   .fillMaxWidth(),
    contentAlignment = Alignment.TopStart,
 ) {
     Column(
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier            
         .wrapContentSize()
         .padding(start = 65.dp)
         .padding(top = 55.dp)
    ) {
         Card(
              shape = MaterialTheme.shapes.medium,  
              modifier = Modifier
             .width(45.dp)
             .height(45.dp)
             .clickable(onClick = {uiClickSound(); resetVictoryCountLogic()})
        ) {
            Image(
                  contentDescription = null,
                  modifier = Modifier.fillMaxSize(),
                  painter = painterResource(R.drawable.trash_icon)
                 )      
               }
         Text(
              text = stringResource(R.string.reset),
              style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
              color = MaterialTheme.colorScheme.error,
              modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}


fun expandButtonLogic(){
expandButton = !expandButton
}


@Composable
fun expandButton(){

 Box(
      modifier = Modifier
     .wrapContentSize(),
      contentAlignment = Alignment.TopStart
 ) {
     Column(
         verticalArrangement = Arrangement.Center,
         modifier = Modifier
        .wrapContentSize()
        .padding(start = 15.dp)
        .padding(top = 60.dp)
    ) {
         Card(
              shape = MaterialTheme.shapes.medium,
              colors = CardDefaults.cardColors(containerColor = Color.Transparent),
              modifier = Modifier
             .width(45.dp)
             .height(35.dp)
             .clickable(onClick = {uiClickSound(); expandButtonLogic()})
       ) {
            Image(
                  contentDescription = null,
                  modifier = Modifier.fillMaxSize(),
                  painter = painterResource(if (expandButton) R.drawable.expand_up_icon
                  else R.drawable.expand_down_icon)
                )      
            }       
        }
    }
}


fun soundEffectsButtonLogic(){
isSoundEffectsOn = !isSoundEffectsOn
editor.putBoolean("isSoundEffectsOn", isSoundEffectsOn)
editor.apply()
}


@Composable
fun soundEffectsButton(){

    Box(
        modifier = Modifier
       .wrapContentSize(),
        contentAlignment = Alignment.TopStart
 ) {
     Column(
             verticalArrangement = Arrangement.Center,
             modifier = Modifier          
            .wrapContentSize()
            .padding(start = 15.dp)
            .padding(top = 95.dp)
    ) {
         Card(
              shape = MaterialTheme.shapes.medium,  
              modifier = Modifier
             .width(45.dp)
             .height(45.dp)
             .clickable(onClick = {uiClickSound(); soundEffectsButtonLogic()})
       ) {
            Image(
                  contentDescription = null,
                  modifier = Modifier.fillMaxSize(),
                  painter = painterResource(if(isSoundEffectsOn) R.drawable.sound_effects_on_icon 
                  else R.drawable.sound_effects_off_icon)
                 )      
               }
         Text(
              text = stringResource(R.string.active_sound),
              style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              modifier = Modifier.align(Alignment.CenterHorizontally)
             )
         }
    }
}


fun backgroundMusicButtonLogic() {

isBackgroundMusicOn = !isBackgroundMusicOn
editor.putBoolean("isBackgroundMusicOn", isBackgroundMusicOn)
editor.apply()
if (isBackgroundMusicOn) startService(Intent(this, MusicService::class.java))
else stopService(Intent(this, MusicService::class.java))
}


@Composable
fun backgroundMusicButton(){

    Box(
        modifier = Modifier
       .wrapContentSize(),
        contentAlignment = Alignment.TopStart
 ) {
     Column(
             verticalArrangement = Arrangement.Center,
             modifier = Modifier
            .wrapContentSize()
            .padding(start = 15.dp)
            .padding(top = 165.dp)
    ) {
         Card(
              shape = MaterialTheme.shapes.medium,
              modifier = Modifier
             .width(45.dp)
             .height(45.dp)
             .clickable(onClick = {uiClickSound(); backgroundMusicButtonLogic()})
       ) {
            Image(
                  contentDescription = null,
                  modifier = Modifier.fillMaxSize(),
                  painter = painterResource( if (isBackgroundMusicOn) R.drawable.sound_on_icon
                  else R.drawable.sound_off_icon)
                 )      
               }
         Text(
              text = stringResource(R.string.active_music),
              style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}


fun restartGameLogic() {
   isCardClicked = List<Boolean>(9) {false}
   isScoreUp = false
   cards.all { false }
   is1Win = false
   isWinSoundPlaying = false
   isGotOldSoundPlaying = false
   expandButton = false
   startX = 0f
   startY = 0f
   endX = 0f
   endY = 0f
}


fun restartButtonLogic(): Boolean{

if ((isCardClicked.all {!it}) || (winner) || (gotOld)) {
return false
}
return true
}
var showRestart = restartButtonLogic()


@Composable
fun restartButton() {

Box(
    modifier = Modifier
   .fillMaxWidth(),
    contentAlignment = Alignment.TopEnd
 ) {
     Column(
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier          
          .wrapContentSize()
          .padding(top = 55.dp, end = 30.dp)
     ) {
          Card(
               shape = MaterialTheme.shapes.medium,
               modifier = Modifier
              .width(45.dp)
              .height(45.dp)
              .clickable(onClick = {uiClickSound(); restartGameLogic()})
          ) {
             Image(
                   contentDescription = null,
                   painter = (restartIcon),
                   modifier = Modifier.wrapContentSize()
                )
             }
          Text(
               text = stringResource(id = R.string.restart),
               style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
               color = MaterialTheme.colorScheme.onSurfaceVariant
           )
       }
   }
}


@Composable
fun playAgain() {

Box(
    modifier = Modifier
   .fillMaxWidth(),
    contentAlignment = Alignment.TopCenter
 ) {
     Column(
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier          
         .fillMaxWidth()
         .padding(top = 210.dp)
     ) {
        Card(     
             shape = MaterialTheme.shapes.large,
             modifier = Modifier
            .width(75.dp)
            .height(75.dp)
            .clickable(onClick = {uiClickSound(); restartGameLogic()})
         ) {
             Image(
                  contentDescription = null,
                  painter = (restartIcon),
                  modifier = Modifier
                 .width(75.dp)
              )
           }
     Text(
          text = stringResource(id = R.string.play_again),
          style = MaterialTheme.typography.bodyLarge,
          color = MaterialTheme.colorScheme.onBackground
         )
      }
   }
}


fun winnerSound(){
if (isSoundEffectsOn){
    if(!isWinSoundPlaying){
        victorySound.start()
        isWinSoundPlaying = true
        }
    }
}


fun gotOldSound(){
if (isSoundEffectsOn){
    if(!isGotOldSoundPlaying){
        gotOldSound.start()
        isGotOldSoundPlaying = true
        }
    }
}


@Composable
fun callFuns(){

/*WHEN ACTIVITY STARTS ->*/ victoryCount(); cardGrid();
displayPlayerNames(); gameStateToast(); expandButton()
if (!play) getPlayerPrefs()
if (play) { checkWinner(); gotOld() }
if (expandButton) { backgroundMusicButton(); soundEffectsButton() }
if (showRestart) restartButton()
if (winner) { winnerSound(); drawWinnerLine(); resetVictoryCount(); playAgain() }
if (gotOld) { gotOldSound(); resetVictoryCount(); playAgain() }
}
callFuns() 
}
    
}//GAMESCREEN FUN ENDS HERE!!

}