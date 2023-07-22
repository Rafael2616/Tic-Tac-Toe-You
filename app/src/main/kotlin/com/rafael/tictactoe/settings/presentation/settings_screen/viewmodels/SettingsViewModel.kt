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

	
    private var _isBackgroundMusicOn = MutableStateFlow(true)
    val isBackgroundMusicOn: StateFlow<Boolean> = _isBackgroundMusicOn
    
    private var _isDarkThemeOn = MutableStateFlow(true)
    val isDarkThemeOn: StateFlow<Boolean> = _isDarkThemeOn

    private var _isDynamicColorsOn = MutableStateFlow(true)
    val isDynamicColorsOn: StateFlow<Boolean> = _isDynamicColorsOn

    private var _isFollowingSystemOn = MutableStateFlow(true)
    val isFollowingSystemOn: StateFlow<Boolean> = _isFollowingSystemOn

    private var _isSoundEffectsOn = MutableStateFlow(true)
    val isSoundEffectsOn: StateFlow<Boolean> = _isSoundEffectsOn
 
    private var _isAboutShown = MutableStateFlow(false)
    val isAboutShown: StateFlow<Boolean> = _isAboutShown

    private var _isContentSetted = MutableStateFlow(true)
    val isContentSetted: StateFlow<Boolean> = _isContentSetted
	
    private var _isActivityFinished = MutableStateFlow(true)
    val isActivityFinished: StateFlow<Boolean> = _isActivityFinished

    private var _isAppInForeground = MutableStateFlow(false)
    val isAppInForeground: StateFlow<Boolean> = _isAppInForeground

    //GET DEFAUT VALUES FOR SETTINGS VARIABLES FROM SETTINGS ENTITY
    val defaultSettings = SettingsEntity.createDefaultSettings()

    //INIT VIEWMODEL VARIABES WITH CORRESPONDING VALUES IN SETTINGS REPOSITORY 
	init {
  	  viewModelScope.launch(Dispatchers.IO) {
	  	  val settings = repository.getAllSettingsValues() ?: defaultSettings
			settings.settingsID = 1
   	     _isBackgroundMusicOn.value = settings.isBackgroundMusicOn
    	    _isDarkThemeOn.value = settings.isDarkThemeOn
    	    _isDynamicColorsOn.value = settings.isDynamicColorsOn
    	    _isFollowingSystemOn.value = settings.isFollowingSystemOn
     	   _isSoundEffectsOn.value = settings.isSoundEffectsOn
  	  }
	}

    //FUNCTIONS TO UPDATE THE VALUES IN SETTINGS REPOSITORY AND VALUES USED TO UPDATE SCREEN
	fun updateIsBackgroundMusicOn(newValue: Boolean, context: Context) {
 	   _isBackgroundMusicOn.value = newValue
 	   viewModelScope.launch(Dispatchers.IO) {
	  	  val settings = repository.getAllSettingsValues() ?: defaultSettings
    		settings.isBackgroundMusicOn = newValue
			repository.setAllSettingsValues(settings)
  	  }
		BackgroundMusic(context, _isBackgroundMusicOn.value)
	}

   fun updateIsDarkThemeOn(newValue: Boolean) {
        _isDarkThemeOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
	  	  val settings = repository.getAllSettingsValues() ?: defaultSettings
    		settings.isDarkThemeOn = newValue
			repository.setAllSettingsValues(settings)
        }
    }

    fun updateIsDynamicColorsOn(newValue: Boolean) {
        _isDynamicColorsOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
	  	  val settings = repository.getAllSettingsValues() ?: defaultSettings
    		settings.isDynamicColorsOn = newValue
			repository.setAllSettingsValues(settings)
        }
    }

    fun updateIsFollowingSystemOn(newValue: Boolean) {
        _isFollowingSystemOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
	  	  val settings = repository.getAllSettingsValues() ?: defaultSettings
    		settings.isFollowingSystemOn = newValue
			repository.setAllSettingsValues(settings)
        }
    }
   
    fun updateIsSoundEffectsOn(newValue: Boolean) {
        _isSoundEffectsOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
    	    val settings = repository.getAllSettingsValues() ?: defaultSettings
			settings.isSoundEffectsOn = newValue
			repository.setAllSettingsValues(settings)
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