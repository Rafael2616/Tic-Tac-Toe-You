package com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

	//Variables that have same values of private vars above, but cant be modified outside this class
    val isBackgroundMusicOn: StateFlow<Boolean> = _isBackgroundMusicOn
    val isDarkThemeOn: StateFlow<Boolean> = _isDarkThemeOn
    val isDynamicColorsOn: StateFlow<Boolean> = _isDynamicColorsOn
    val isFollowingSystemOn: StateFlow<Boolean> = _isFollowingSystemOn
    val isSoundEffectsOn: StateFlow<Boolean> = _isSoundEffectsOn
    val isAboutShown: StateFlow<Boolean> = _isAboutShown

    //Initialize ViewModel StateFlows Variables with corresponding values in SettingsRepository
	init {
  	  viewModelScope.launch(Dispatchers.IO) {
	    var settings = repository.getAllSettingsValues(1).first()  ?: defaultSettings
   	 	_isBackgroundMusicOn.value = settings.isBackgroundMusicOn
    	    _isDarkThemeOn.value = settings.isDarkThemeOn
    	    _isDynamicColorsOn.value = settings.isDynamicColorsOn
            _isFollowingSystemOn.value = settings.isFollowingSystemOn
            _isSoundEffectsOn.value = settings.isSoundEffectsOn
  	  }
	}
	
    //Functions to update values in SettingsRepository (if needed) and SettingsViewModel StateFlows to notify @Composables to update
	fun updateIsBackgroundMusicOn(newValue: Boolean, context: Context) {
 	   _isBackgroundMusicOn.value = newValue	
		viewModelScope.launch(Dispatchers.IO) {
            var settings = repository.getAllSettingsValues(1).first() ?: defaultSettings
            settings.isBackgroundMusicOn = _isBackgroundMusicOn.value
			repository.setAllSettingsValues(settings)
		}
		BackgroundMusic(context, _isBackgroundMusicOn.value)
	}

   fun updateIsDarkThemeOn(newValue: Boolean) {
        _isDarkThemeOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
	  	  var settings = repository.getAllSettingsValues(1).first() ?: defaultSettings
    		settings.isDarkThemeOn = _isDarkThemeOn.value
			repository.setAllSettingsValues(settings)
        }
    }

    fun updateIsDynamicColorsOn(newValue: Boolean) {
        _isDynamicColorsOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            var settings = repository.getAllSettingsValues(1).first() ?: defaultSettings
    		settings.isDynamicColorsOn = _isDynamicColorsOn.value
			repository.setAllSettingsValues(settings)
        }
    }

    fun updateIsFollowingSystemOn(newValue: Boolean) {
        _isFollowingSystemOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
	  	  var settings = repository.getAllSettingsValues(1).first() ?: defaultSettings
    		settings.isFollowingSystemOn = _isFollowingSystemOn.value
			repository.setAllSettingsValues(settings)
        }
    }
   
    fun updateIsSoundEffectsOn(newValue: Boolean) {
        _isSoundEffectsOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
            var settings = repository.getAllSettingsValues(1).first() ?: defaultSettings
			settings.isSoundEffectsOn = _isSoundEffectsOn.value
			repository.setAllSettingsValues(settings)
        }
    }
 
    fun updateIsAboutShown(newValue: Boolean) {
        _isAboutShown.value = newValue
    }
}