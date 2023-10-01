package com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.game.domain.util.BackgroundMusic
import com.rafael.tictactoe.settings.domain.model.SettingsEntity
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository,
    private val player: ExoPlayer
) : ViewModel() {

    //Get default values for settings variables from SettingsEntity (used to avoid NPEs)
    val defaultSettings = SettingsEntity.getDefaultSettings()

    //Private vars that are updated in update functions but only acessible in this class
    private var _isBackgroundMusicOn = MutableStateFlow(true)
    private var _isDarkThemeOn = MutableStateFlow(true)
    private var _isDynamicColorsOn = MutableStateFlow(true)
    private var _isFollowingSystemOn = MutableStateFlow(true)
    private var _isSoundEffectsOn = MutableStateFlow(true)
    private var _isAboutShown = MutableStateFlow(false)  
    private var _showWinnerLineColorDialog = MutableStateFlow(false)
    private var _winnerLineColor = MutableStateFlow(Color.Yellow)
    private var _selectedColorOption = MutableStateFlow(2)
    private var _isBMPlaying = MutableStateFlow(false)

	//Variables that have same values of private vars above, but cant be modified outside this class
    val isBackgroundMusicOn: StateFlow<Boolean> = _isBackgroundMusicOn
    val isDarkThemeOn: StateFlow<Boolean> = _isDarkThemeOn
    val isDynamicColorsOn: StateFlow<Boolean> = _isDynamicColorsOn
    val isFollowingSystemOn: StateFlow<Boolean> = _isFollowingSystemOn
    val isSoundEffectsOn: StateFlow<Boolean> = _isSoundEffectsOn
    val isAboutShown: StateFlow<Boolean> = _isAboutShown
    val showWinnerLineColorDialog: StateFlow<Boolean> = _showWinnerLineColorDialog
    val winnerLineColor: StateFlow<Color> = _winnerLineColor
    val selectedColorOption: StateFlow<Int> = _selectedColorOption
    val isBMPlaying: StateFlow<Boolean> = _isBMPlaying

    //Initialize ViewModel StateFlows Variables with corresponding values in SettingsRepository
	init {
  	  viewModelScope.launch(Dispatchers.IO) {
            val settings = repository.getAllSettingsValues(1)  ?: defaultSettings
   	 	_isBackgroundMusicOn.value = settings.isBackgroundMusicOn
    	    _isDarkThemeOn.value = settings.isDarkThemeOn
    	    _isDynamicColorsOn.value = settings.isDynamicColorsOn
            _isFollowingSystemOn.value = settings.isFollowingSystemOn
            _isSoundEffectsOn.value = settings.isSoundEffectsOn
  	  }
	}
	
    //Functions to update values in SettingsRepository and SettingsViewModel StateFlows
	fun updateIsBackgroundMusicOn(newValue: Boolean, context: Context) {
 	   _isBackgroundMusicOn.value = newValue
		viewModelScope.launch(Dispatchers.IO) {
            var settings = repository.getAllSettingsValues(1) ?: defaultSettings
            settings.isBackgroundMusicOn = newValue
			repository.setAllSettingsValues(settings)
		}
        BackgroundMusic(context, this)
    }

   fun updateIsDarkThemeOn(newValue: Boolean) {
        _isDarkThemeOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
	  	  var settings = repository.getAllSettingsValues(1) ?: defaultSettings
    		settings.isDarkThemeOn = newValue
			repository.setAllSettingsValues(settings)
        }
    }

    fun updateIsDynamicColorsOn(newValue: Boolean) {
        _isDynamicColorsOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            var settings = repository.getAllSettingsValues(1) ?: defaultSettings
    		settings.isDynamicColorsOn = newValue
			repository.setAllSettingsValues(settings)
        }
    }

    fun updateIsFollowingSystemOn(newValue: Boolean) {
        _isFollowingSystemOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
	  	  var settings = repository.getAllSettingsValues(1) ?: defaultSettings
    		settings.isFollowingSystemOn = newValue
			repository.setAllSettingsValues(settings)
        }
    }
   
    fun updateIsSoundEffectsOn(newValue: Boolean) {
        _isSoundEffectsOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            var settings = repository.getAllSettingsValues(1) ?: defaultSettings
			settings.isSoundEffectsOn = newValue
			repository.setAllSettingsValues(settings)
        }
    }
 
    fun updateIsAboutShown(newValue: Boolean) {
        _isAboutShown.value = newValue
    }
    
    fun showWinnerLineColorDialog(newValue: Boolean) {
        _showWinnerLineColorDialog.value = newValue
    }

    fun setWinnerLineColor(newValue: Color) {
        _winnerLineColor.value = newValue
    }

    fun updateSelectedColorOption(newValue: Int) {
        _selectedColorOption.value = newValue
    }
    
    fun execUIClickSound(context: Context) {
        if (_isSoundEffectsOn.value) {
            UiClickSound(context, player)
        }
    }

    fun updateIsBMPlaying(newValue: Boolean) {
        _isBMPlaying.value = newValue
    }
}