package com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels

import android.app.Application
import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
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
    private val context: Application
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
                isSoundEffectsOn = settings.isSoundEffectsOn
            )}
  	  }
	}
	
	fun onEvent(event: SettingsEvent) {
        when (event) {
 	       SettingsEvent.toggleIsBackgroundMusicOn -> {
                _state.update { it.copy(isBackgroundMusicOn = !it.isBackgroundMusicOn) }

	    	    viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isBackgroundMusicOn = state.value.isBackgroundMusicOn
			        repository.setAllSettingsValues(settings)
                }
                execUIClickSound()
                BackgroundMusic(context, this::onEvent, state.value)
            }
 	       is SettingsEvent.toggleIsDarkThemeOn -> {
                _state.update { it.copy(isDarkThemeOn = event.value) }

	    	    viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isDarkThemeOn = state.value.isDarkThemeOn
			        repository.setAllSettingsValues(settings)
                }
                if (event.withSound) {
                    execUIClickSound()
                }
            }
 	       SettingsEvent.toggleIsDynamicColorsOn -> {
                _state.update { it.copy(isDynamicColorsOn = !it.isDynamicColorsOn) }

	    	    viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isDynamicColorsOn = state.value.isDynamicColorsOn
			        repository.setAllSettingsValues(settings)
                }
                execUIClickSound()
            }
 	       SettingsEvent.toggleIsFollowingSystemOn -> {
                _state.update { it.copy(isFollowingSystemOn = !it.isFollowingSystemOn) }

	    	    viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isFollowingSystemOn = state.value.isFollowingSystemOn
			        repository.setAllSettingsValues(settings)
                }
                execUIClickSound()
            }
 	       SettingsEvent.toggleIsSoundEffectsOn -> {
                _state.update { it.copy(isSoundEffectsOn = !it.isSoundEffectsOn) }

	    	    viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isSoundEffectsOn = state.value.isSoundEffectsOn
			        repository.setAllSettingsValues(settings)
                }
                execUIClickSound()
            }
 	       SettingsEvent.onSettingsScreenDestroyment -> {
                _state.update { it.copy(
                    isAboutShown = false,
                    showColorSelectionDialog = false
                )}
            }
 	       is SettingsEvent.updateIsAboutShown -> {
                _state.update { it.copy(isAboutShown = event.value)}
                execUIClickSound()
            }
 	       is SettingsEvent.setCustomizationType -> {
                _state.update { it.copy(customizationType = event.type) }
                execUIClickSound()
            }
 	       is SettingsEvent.showColorSelectionDialog -> {
                _state.update { it.copy(showColorSelectionDialog = event.value)}
                execUIClickSound()
            }
 	       is SettingsEvent.setWinnerLineColor -> {
                _state.update { it.copy(winnerLineColor = event.color) }
                execUIClickSound()
            }
 	       is SettingsEvent.setXColor -> {
                _state.update { it.copy(xColor = event.color)}
                execUIClickSound()
            }
 	       is SettingsEvent.setCircleColor -> {
                _state.update { it.copy(circleColor = event.color) }
                execUIClickSound()
            }
 	       is SettingsEvent.selectedWinnerLineColor -> {
                _state.update { it.copy(selectedWinnerLineColor = event.value) }
                execUIClickSound()
            }
 	       is SettingsEvent.selectedXColor -> {
                _state.update { it.copy(selectedXColor = event.value) }
                execUIClickSound()
            }
 	       is SettingsEvent.selectedCircleColor -> {
                _state.update { it.copy(selectedCircleColor = event.value) }
                execUIClickSound()
            }
 	       is SettingsEvent.isBGMusicPlaying -> {
                _state.update { it.copy(isBMPlaying = event.value) }
            }
		}
    }

    fun execUIClickSound() {
        if (state.value.isSoundEffectsOn) {
            UiClickSound(context, player)
        }
    }
}