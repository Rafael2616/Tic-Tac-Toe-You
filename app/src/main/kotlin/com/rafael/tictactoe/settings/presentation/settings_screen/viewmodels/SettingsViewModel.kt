package com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
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

    //GET DEFAUT VALUES FOR SETTINGS VARIABLES FROM SETTINGS ENTITY
    val defaultSettings = SettingsEntity.createDefaultSettings()

    //INIT VIEWMODEL VARIABES WITH CORRESPONDING VALUES IN SETTINGS REPOSITORY 
	init {
  	  viewModelScope.launch(Dispatchers.IO) {
	  	  val settings = repository.getAllSettingsValues() ?: defaultSettings
   	     _isBackgroundMusicOn.value = settings.isBackgroundMusicOn
    	    _isDarkThemeOn.value = settings.isDarkThemeOn
    	    _isDynamicColorsOn.value = settings.isDynamicColorsOn
    	    _isFollowingSystemOn.value = settings.isFollowingSystemOn
     	   _isSoundEffectsOn.value = settings.isSoundEffectsOn
  	  }
	}

    //FUNS TO UPDATE THE VALUES OF VARIABLES IN REPOSITORY AND VIEWMODEL TO UPDATE SCREEN
	fun updateIsBackgroundMusicOnValue(newValue: Boolean) {
 	   _isBackgroundMusicOn.value = newValue
 	   viewModelScope.launch(Dispatchers.IO) {
	  	  val settings = repository.getAllSettingsValues() ?: defaultSettings
    		settings.isBackgroundMusicOn = newValue
			repository.setAllSettingsValues(settings)
  	  }
	}

   fun updateIsDarkThemeOnValue(newValue: Boolean) {
        _isDarkThemeOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
	  	  val settings = repository.getAllSettingsValues() ?: defaultSettings
    		settings.isDarkThemeOn = newValue
			repository.setAllSettingsValues(settings)
        }
    }

    fun updateIsDynamicColorsOnValue(newValue: Boolean) {
        _isDynamicColorsOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
	  	  val settings = repository.getAllSettingsValues() ?: defaultSettings
    		settings.isDynamicColorsOn = newValue
			repository.setAllSettingsValues(settings)
        }
    }

    fun updateIsFollowingSystemOnValue(newValue: Boolean) {
        _isFollowingSystemOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
	  	  val settings = repository.getAllSettingsValues() ?: defaultSettings
    		settings.isFollowingSystemOn = newValue
			repository.setAllSettingsValues(settings)
        }
    }
   
    fun updateIsSoundEffectsOnValue(newValue: Boolean) {
        _isSoundEffectsOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
    	    val settings = repository.getAllSettingsValues() ?: defaultSettings
			settings.isSoundEffectsOn = newValue
			repository.setAllSettingsValues(settings)
        }
    }

    fun updateIsAboutShownValue(newValue: Boolean) {
        _isAboutShown.value = newValue
    }
}