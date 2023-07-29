package com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels

import android.content.*
import android.widget.Toast
import androidx.lifecycle.*
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.game.domain.util.BackgroundMusic
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.domain.model.SettingsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository
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
    private var _isContentSetted = MutableStateFlow(true)
    private var _isActivityFinished = MutableStateFlow(true)
    private var _isAppInForeground = MutableStateFlow(false)
	
	//Variables that have same values of private vars above, but cant be modified outside this class
    val isBackgroundMusicOn: StateFlow<Boolean> = _isBackgroundMusicOn
    val isDarkThemeOn: StateFlow<Boolean> = _isDarkThemeOn
    val isDynamicColorsOn: StateFlow<Boolean> = _isDynamicColorsOn
    val isFollowingSystemOn: StateFlow<Boolean> = _isFollowingSystemOn
    val isSoundEffectsOn: StateFlow<Boolean> = _isSoundEffectsOn
    val isAboutShown: StateFlow<Boolean> = _isAboutShown
    val isContentSetted: StateFlow<Boolean> = _isContentSetted	
    val isActivityFinished: StateFlow<Boolean> = _isActivityFinished
    val isAppInForeground: StateFlow<Boolean> = _isAppInForeground

    //Initialize ViewModel StateFlows Variables with corresponding values in SettingsRepository
	init {
  	  viewModelScope.launch(Dispatchers.IO) {
	  	  repository.getAllSettingsValues().collect { settingsValues ->
				val settings = settingsValues ?: defaultSettings
   	 	    _isBackgroundMusicOn.value = settings.isBackgroundMusicOn
    	 	   _isDarkThemeOn.value = settings.isDarkThemeOn
    	 	   _isDynamicColorsOn.value = settings.isDynamicColorsOn
    		    _isFollowingSystemOn.value = settings.isFollowingSystemOn
     		   _isSoundEffectsOn.value = settings.isSoundEffectsOn
			}
  	  }
	}
	
    //Functions to update values in SettingsRepository (if needed) and SettingsViewModel StateFlows to notify @Composables to update
	fun updateIsBackgroundMusicOn(newValue: Boolean, context: Context) {
 	   _isBackgroundMusicOn.value = newValue	
		viewModelScope.launch(Dispatchers.IO) {
			repository.getAllSettingsValues().collect { currentSettings ->
				var newSettings = currentSettings ?: defaultSettings
    			newSettings.isBackgroundMusicOn = newValue
				repository.setAllSettingsValues(newSettings)
  	  	}
		}
		BackgroundMusic(context, _isBackgroundMusicOn.value)
	}

   fun updateIsDarkThemeOn(newValue: Boolean) {
        _isDarkThemeOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
	  	  repository.getAllSettingsValues().collect { currentSettings ->
				var newSettings = currentSettings ?: defaultSettings
    	    	newSettings.isDarkThemeOn = newValue
				repository.setAllSettingsValues(newSettings)
			}
        }
    }

    fun updateIsDynamicColorsOn(newValue: Boolean) {
        _isDynamicColorsOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
	  	  repository.getAllSettingsValues().collect { currentSettings ->
				var newSettings = currentSettings ?: defaultSettings
    			newSettings.isDynamicColorsOn = newValue
				repository.setAllSettingsValues(newSettings)
			}
        }
    }

    fun updateIsFollowingSystemOn(newValue: Boolean) {
        _isFollowingSystemOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
	  	  repository.getAllSettingsValues().collect { currentSettings ->
				var newSettings = currentSettings ?: defaultSettings
    			newSettings.isFollowingSystemOn = newValue
				repository.setAllSettingsValues(newSettings)
			}
        }
    }
   
    fun updateIsSoundEffectsOn(newValue: Boolean) {
        _isSoundEffectsOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
    	    repository.getAllSettingsValues().collect { currentSettings ->
				var newSettings = currentSettings ?: defaultSettings
		    	newSettings.isSoundEffectsOn = newValue
				repository.setAllSettingsValues(newSettings)
			}
        }
    }

    fun updateIsAboutShown(newValue: Boolean) {
        _isAboutShown.value = newValue
    }
	
    fun updateIsContentSetted(newValue: Boolean) {
        _isContentSetted.value = newValue
    }
	
    fun updateIsActivityFinished(newValue: Boolean) {
        _isActivityFinished.value = newValue
    }
    fun setIsAppInForeground(newValue: Boolean) {
        _isAppInForeground.value = newValue
    }
}