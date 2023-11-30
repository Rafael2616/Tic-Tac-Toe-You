package com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels

import android.app.Application
import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.game.domain.audio.BackgroundMusic
import com.rafael.tictactoe.settings.domain.enums.Colors
import com.rafael.tictactoe.settings.domain.enums.Customization
import com.rafael.tictactoe.settings.domain.model.SettingsEntity
import com.rafael.tictactoe.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.domain.model.SettingsState
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository,
    private val player: ExoPlayer,
    private val context: Application,
    private val service: MusicService
) : ViewModel() {

    //Get default values for settings variables from SettingsEntity (used to avoid NPEs)
    val defaultSettings = SettingsEntity.getDefaultSettings()

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    //Initialize ViewModel StateFlows Variables with corresponding values in SettingsRepository
	init {
  	  viewModelScope.launch(Dispatchers.IO) {
            val settings = repository.getAllSettingsValues(1) ?: defaultSettings
            _state.update { it.copy(
   	 	    isBackgroundMusicOn = settings.isBackgroundMusicOn,
    	        isDarkThemeOn = settings.isDarkThemeOn,
    	        isDynamicColorsOn = settings.isDynamicColorsOn,
                isFollowingSystemOn = settings.isFollowingSystemOn,
                isSoundEffectsOn = settings.isSoundEffectsOn,
                selectedWinnerLineColor = settings.selectedWinnerLineColor,
                selectedXColor = settings.selectedXColor,
                selectedCircleColor = settings.selectedCircleColor,
                winnerLineColor = Color(settings.selectedWinnerLineColor),
                xColor = Color(settings.selectedXColor),
                circleColor = Color(settings.selectedCircleColor)
            )}
  	  }
	}
	
	fun onEvent(event: SettingsEvent) {
        when (event) {
 	       SettingsEvent.ToggleIsBackgroundMusicOn -> {
                _state.update { it.copy(isBackgroundMusicOn = !it.isBackgroundMusicOn) }

	    	    viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isBackgroundMusicOn = state.value.isBackgroundMusicOn
			        repository.setAllSettingsValues(settings)

                    BackgroundMusic(
                        context = context,
                        onEvent = this@SettingsViewModel::onEvent,
                        isBMPlaying = state.value.isBMPlaying,
                        isBackgroundMusicOn = state.value.isBackgroundMusicOn,
                        service = service
                    )
                }
                execUIClickSound()
            }
 	       SettingsEvent.ToggleIsDarkThemeOn -> {
                _state.update { it.copy(isDarkThemeOn = !it.isDarkThemeOn) }

	    	    viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isDarkThemeOn = state.value.isDarkThemeOn
			        repository.setAllSettingsValues(settings)
                }
                execUIClickSound()
            }
 	       SettingsEvent.ToggleIsDynamicColorsOn -> {
                _state.update { it.copy(isDynamicColorsOn = !it.isDynamicColorsOn) }

	    	    viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isDynamicColorsOn = state.value.isDynamicColorsOn
			        repository.setAllSettingsValues(settings)
                }
                execUIClickSound()
            }
 	       SettingsEvent.ToggleIsFollowingSystemOn -> {
                _state.update { it.copy(isFollowingSystemOn = !it.isFollowingSystemOn) }

	    	    viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isFollowingSystemOn = state.value.isFollowingSystemOn
			        repository.setAllSettingsValues(settings)
                }
                execUIClickSound()
            }
 	       SettingsEvent.ToggleIsSoundEffectsOn -> {
                _state.update { it.copy(isSoundEffectsOn = !it.isSoundEffectsOn) }

	    	    viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isSoundEffectsOn = state.value.isSoundEffectsOn
			        repository.setAllSettingsValues(settings)
                }
                execUIClickSound()
            }
 	       is SettingsEvent.SetIsDarkThemeOn -> {
                _state.update { it.copy(isDarkThemeOn = event.value) }

	    	    viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isDarkThemeOn = state.value.isDarkThemeOn
			        repository.setAllSettingsValues(settings)
                }
            }
 	       SettingsEvent.OnSettingsScreenDestroyment -> {
                _state.update { it.copy(
                    isAboutShown = false,
                    showColorSelectionDialog = false
                )}
            }
            is SettingsEvent.StartBackgroundMusic -> {
	    	    viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    BackgroundMusic(
                        context = context,
                        isBMPlaying = state.value.isBMPlaying,
                        isBackgroundMusicOn = settings.isBackgroundMusicOn,
                        onEvent = this@SettingsViewModel::onEvent,
                        service = service,
                        isAppInOnStop = event.value
                    )
                }
            }
 	       is SettingsEvent.ShowAboutAppCard -> {
                _state.update { it.copy(isAboutShown = event.value)}
                execUIClickSound()
            }
 	       is SettingsEvent.SetCustomizationType -> {
                _state.update { it.copy(customizationType = event.type) }
                execUIClickSound()
            }
 	       is SettingsEvent.ShowColorSelectionDialog -> {
                _state.update { it.copy(showColorSelectionDialog = event.value)}
                execUIClickSound()
            }
 	       is SettingsEvent.SetWinnerLineColor -> {
                _state.update { it.copy(winnerLineColor = event.color) }
                execUIClickSound()
            }
 	       is SettingsEvent.SetXColor -> {

                _state.update { it.copy(xColor = event.color)}
                execUIClickSound()
            }
 	       is SettingsEvent.SetCircleColor -> {
                _state.update { it.copy(circleColor = event.color) }
                execUIClickSound()
            }
 	       is SettingsEvent.SelectedWinnerLineColor -> {
                viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.selectedWinnerLineColor = event.value
			        repository.setAllSettingsValues(settings)
                }
                _state.update { it.copy(selectedWinnerLineColor = event.value) }
                execUIClickSound()
            }
 	       is SettingsEvent.SelectedXColor -> {
                viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.selectedXColor = event.value
			        repository.setAllSettingsValues(settings)
                }
                _state.update { it.copy(selectedXColor = event.value) }
                execUIClickSound()
            }
 	       is SettingsEvent.SelectedCircleColor -> {
                viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.selectedCircleColor = event.value
			        repository.setAllSettingsValues(settings)
                }
                _state.update { it.copy(selectedCircleColor = event.value) }
                execUIClickSound()
            }
 	       is SettingsEvent.IsBGMusicPlaying -> {
                _state.update { it.copy(isBMPlaying = event.value) }
            }
		}
    }

    fun Color(option: Int): Color {
        val color = Colors.get()
        return when (option) {
            0 -> color.blue
            1 -> color.red
            2 -> color.yellow
            3 -> color.burnt_yellow
            4 -> color.dark_green
            5 -> color.light_green
            6 -> color.purple
            7 -> color.magenta
            8 -> color.white
            else -> color.cyan
        }
    }

    fun execUIClickSound() {
        if (state.value.isSoundEffectsOn) {
            UiClickSound(context, player)
        }
    }
}