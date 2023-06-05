package com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.rafael.tictactoe.settings.domain.use_case.SettingsUseCases
import com.rafael.tictactoe.settings.domain.util.SettingsUtilities
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsUseCase: SettingsUseCases,
    private val settingsUtil: SettingsUtilities,
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


init {
    viewModelScope.launch(Dispatchers.IO) {
        val settingsValues = settingsUtil.getSettingsValues()
        settingsValues?.let {
        _isBackgroundMusicOn.value = it.isBackgroundMusicOn
        _isDarkThemeOn.value = it.isDarkThemeOn
        _isDynamicColorsOn.value = it.isDynamicColorsOn
        _isFollowingSystemOn.value = it.isFollowingSystemOn
        _isSoundEffectsOn.value = it.isSoundEffectsOn
        }
    }
}
/*
init {
    viewModelScope.launch(Dispatchers.IO) {
        val settingsValues = settingsRepo.getBackgroundMusicValue()    
        _isBackgroundMusicOn.value = settingsValues
    }
}*/

    fun updateIsBackgroundMusicOnValue(newValue: Boolean) {
        _isBackgroundMusicOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
        //  val bgMsc = repository.toggleAllSettingsValues()
        }
    }

    fun updateIsDarkThemeOnValue(newValue: Boolean) {
        _isDarkThemeOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
           // settingsUseCase.toggleDarkTheme(isDarkThemeOn.value)
        }
    }

    fun updateIsDynamicColorsOnValue(newValue: Boolean) {
        _isDynamicColorsOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
          //  settingsUseCase.toggleDynamicColors(isDynamicColorsOn.value)
        }
    }

    fun updateIsFollowingSystemOnValue(newValue: Boolean) {
        _isFollowingSystemOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
         //  settingsUseCase.toggleFollowingSystem(isFollowingSystemOn.value)
        }
    }
   
    fun updateIsSoundEffectsOnValue(newValue: Boolean) {
        _isSoundEffectsOn.value = newValue
        viewModelScope.launch(Dispatchers.IO) {
        //  settingsUseCase.toggleSoundEffects(isSoundEffectsOn.value)
        }
    }

    fun updateIsAboutShownValue(newValue: Boolean) {
        _isAboutShown.value = newValue
    }
}