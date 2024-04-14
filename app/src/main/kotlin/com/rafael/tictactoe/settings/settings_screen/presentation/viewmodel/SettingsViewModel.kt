package com.rafael.tictactoe.settings.settingsScreen.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.common.domain.util.ProfilePictureUtils
import com.rafael.tictactoe.common.domain.util.getVersionName
import com.rafael.tictactoe.common.domain.util.toColor
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.game.domain.audio.backgroundMusic
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEntity
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsScreen.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.FileInputStream
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository,
    private val player: ExoPlayer,
    private val context: Application,
    private val service: MusicService,
) : ViewModel() {

    // Get default values for settings from SettingsEntity for first app usage
    val defaultSettings = SettingsEntity.getDefaultSettings()

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    // Initialize ViewModel StateFlows variables with corresponding values from SettingsRepository
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val settings = repository.getAllSettingsValues(1) ?: defaultSettings
            _state.update {
                it.copy(
                    isBackgroundMusicOn = settings.isBackgroundMusicOn,
                    isDarkThemeOn = settings.isDarkThemeOn,
                    isDynamicColorsOn = settings.isDynamicColorsOn,
                    isFollowingSystemOn = settings.isFollowingSystemOn,
                    isSoundEffectsOn = settings.isSoundEffectsOn,
                    selectedWinnerLineColor = settings.selectedWinnerLineColor,
                    selectedXColor = settings.selectedXColor,
                    selectedCircleColor = settings.selectedCircleColor,
                    winnerLineColor = settings.selectedWinnerLineColor.toColor(),
                    xColor = settings.selectedXColor.toColor(),
                    circleColor = settings.selectedCircleColor.toColor(),
                    appVersion = settings.appVersion,
                    showWhatsNewDialog = if (settings.appVersion == "O" || settings.appVersion != getVersionName()) {
                        true
                    } else {
                        false
                    },
                    shapeSelected = settings.shapeSelected,
                    boardCardsCornerRadius = settings.boardCardsCornerRadius,
                    boardCardsThickness = settings.boardCardsThickness,
                    boardCardsElevation = settings.boardCardsElevation,
                    boardCardsShadow = settings.boardCardsShadow,
                    boardCardsBorderColor = Color("${settings.boardCardsBorderColor}".toLong(16)),
                    isRGBEffectOn = settings.isRGBEffectOn,
                    isGradientOn = settings.isGradientOn,
                )
            }
            backgroundMusic(
                context = context,
                isBMPlaying = state.value.isBMPlaying,
                isBackgroundMusicOn = settings.isBackgroundMusicOn,
                service = service,
                isAppInOnStop = false,
            )?.let { isPlaying ->
                _state.update { it.copy(isBMPlaying = isPlaying) }
            }
            _state.update { it.copy(profilePictureUri = ProfilePictureUtils().getPictureUriIfExists()) }
            onSaveImage(Uri.EMPTY)
            this.cancel()
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
                }
                backgroundMusic(
                    context = context,
                    isBMPlaying = state.value.isBMPlaying,
                    isBackgroundMusicOn = state.value.isBackgroundMusicOn,
                    service = service,
                )?.let { isPlaying ->
                    _state.update { it.copy(isBMPlaying = isPlaying) }
                }
            }
            SettingsEvent.ToggleIsDarkThemeOn -> {
                _state.update { it.copy(isDarkThemeOn = !it.isDarkThemeOn) }

                viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isDarkThemeOn = state.value.isDarkThemeOn
                    repository.setAllSettingsValues(settings)
                }
            }
            SettingsEvent.ToggleIsDynamicColorsOn -> {
                _state.update { it.copy(isDynamicColorsOn = !it.isDynamicColorsOn) }

                viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isDynamicColorsOn = state.value.isDynamicColorsOn
                    repository.setAllSettingsValues(settings)
                }
            }
            SettingsEvent.ToggleIsFollowingSystemOn -> {
                _state.update { it.copy(isFollowingSystemOn = !it.isFollowingSystemOn) }

                viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isFollowingSystemOn = state.value.isFollowingSystemOn
                    repository.setAllSettingsValues(settings)
                }
            }
            SettingsEvent.ToggleIsSoundEffectsOn -> {
                _state.update { it.copy(isSoundEffectsOn = !it.isSoundEffectsOn) }

                viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isSoundEffectsOn = state.value.isSoundEffectsOn
                    repository.setAllSettingsValues(settings)
                }
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
                _state.update {
                    it.copy(
                        isAboutShown = false,
                        showColorSelectionDialog = false,
                    )
                }
            }
            is SettingsEvent.StartBackgroundMusic -> {
                backgroundMusic(
                    context = context,
                    isBMPlaying = state.value.isBMPlaying,
                    isBackgroundMusicOn = state.value.isBackgroundMusicOn,
                    service = service,
                    isAppInOnStop = event.value,
                )?.let { isPlaying ->
                    _state.update { it.copy(isBMPlaying = isPlaying) }
                }
            }
            is SettingsEvent.ShowAboutAppCard -> {
                _state.update { it.copy(isAboutShown = event.value) }
            }
            is SettingsEvent.SetCustomizationType -> {
                _state.update { it.copy(customizationType = event.type) }
            }
            is SettingsEvent.ShowColorSelectionDialog -> {
                _state.update { it.copy(showColorSelectionDialog = event.value) }
            }
            is SettingsEvent.SetWinnerLineColor -> {
                _state.update { it.copy(winnerLineColor = event.color) }
            }
            is SettingsEvent.SetXColor -> {
                _state.update { it.copy(xColor = event.color) }
            }
            is SettingsEvent.SetCircleColor -> {
                _state.update { it.copy(circleColor = event.color) }
            }
            is SettingsEvent.SelectedWinnerLineColor -> {
                viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.selectedWinnerLineColor = event.value
                    repository.setAllSettingsValues(settings)
                }
                _state.update { it.copy(selectedWinnerLineColor = event.value) }
            }
            is SettingsEvent.SelectedXColor -> {
                viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.selectedXColor = event.value
                    repository.setAllSettingsValues(settings)
                }
                _state.update { it.copy(selectedXColor = event.value) }
            }
            is SettingsEvent.SelectedCircleColor -> {
                viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.selectedCircleColor = event.value
                    repository.setAllSettingsValues(settings)
                }
                _state.update { it.copy(selectedCircleColor = event.value) }
            }
            SettingsEvent.HideWhatsNewDialog -> {
                _state.update { it.copy(showWhatsNewDialog = false) }
                viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.appVersion = getVersionName()
                    repository.setAllSettingsValues(settings)
                }
            }
            SettingsEvent.ShowWhatsNewDialog -> {
                _state.update { it.copy(showWhatsNewDialog = true) }
            }
            SettingsEvent.ToggleShowProfilePictureMenu -> {
                _state.update { it.copy(showProfilePictureMenu = !it.showProfilePictureMenu) }
            }
            SettingsEvent.HideProfilePictureMenu -> {
                _state.update { it.copy(showProfilePictureMenu = false) }
            }
            is SettingsEvent.ShowBoardCustomizationDialog -> {
                _state.update { it.copy(showBoardCustomizationDialog = event.value) }
            }
            is SettingsEvent.SetBoardShape -> {
                _state.update {
                    it.copy(
                        shapeSelected = event.index,
                        boardCardsCornerRadius = event.shape.cornerRadius,
                        boardCardsThickness = event.shape.thickness,
                        boardCardsElevation = event.shape.elevation,
                        boardCardsShadow = event.shape.shadow,
                        boardCardsBorderColor = event.shape.borderColor,
                        isGradientOn = event.isGradientOn,
                        isRGBEffectOn = event.isRGBEffectOn,
                    )
                }
                viewModelScope.launch(Dispatchers.IO) {
                    var settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.shapeSelected = event.index
                    settings.boardCardsCornerRadius = event.shape.cornerRadius
                    settings.boardCardsThickness = event.shape.thickness
                    settings.boardCardsElevation = event.shape.elevation
                    settings.boardCardsShadow = event.shape.shadow
                    settings.boardCardsBorderColor = event.shape.borderHexColor
                    settings.isRGBEffectOn = event.isRGBEffectOn
                    settings.isGradientOn = event.isGradientOn

                    repository.setAllSettingsValues(settings)
                }
            }
            is SettingsEvent.UpdateBoardCardsBorderColor -> {
                _state.update { it.copy(boardCardsBorderColor = event.color) }
            }
        }
    }

    fun onDeleteImage() {
        _state.update {
            it.copy(
                profilePictureUri = Uri.EMPTY,
                profilePictureBitmap = null,
                showProfilePictureMenu = false,
            )
        }
        ProfilePictureUtils().deletePicture()
    }

    fun onSaveImage(imageUri: Uri) {
        if (imageUri != Uri.EMPTY) {
            _state.update {
                it.copy(
                    profilePictureUri = ProfilePictureUtils().savePictureFromUri(context as Context, imageUri),
                    showProfilePictureMenu = false,
                )
            }
        }
        if (state.value.profilePictureUri != Uri.EMPTY) {
            _state.update {
                it.copy(
                    profilePictureBitmap = BitmapFactory.decodeStream(
                        FileInputStream(it.profilePictureUri.path!!),
                    ),
                    showProfilePictureMenu = false,
                )
            }
        }
    }
}
