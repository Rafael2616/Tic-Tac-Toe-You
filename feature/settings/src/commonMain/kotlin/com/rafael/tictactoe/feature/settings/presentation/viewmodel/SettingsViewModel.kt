package com.rafael.tictactoe.feature.settings.presentation.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.Uri
import com.rafael.tictactoe.core.domain.util.BuildInfo.VERSION_NAME
import com.rafael.tictactoe.core.domain.util.toColor
import com.rafael.tictactoe.core.platform.dispatchers.IO
import com.rafael.tictactoe.core.services.music.backgroundMusic
import com.rafael.tictactoe.core.settings.domain.model.SettingsEntity
import com.rafael.tictactoe.core.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.core.settings.domain.model.SettingsState
import com.rafael.tictactoe.core.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.feature.settings.data.utils.toByteArray
import com.rafael.tictactoe.feature.settings.domain.profile.ProfilePictureUtilsImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap

@OptIn(ExperimentalResourceApi::class)
class SettingsViewModel(
    private val repository: SettingsRepository,
) : ViewModel() {

    // Get default values for settings from SettingsEntity for first app usage
    private val defaultSettings = SettingsEntity.getDefaultSettings()

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
                    showWhatsNewDialog = settings.appVersion == "O" || settings.appVersion != VERSION_NAME,
                    shapeSelected = settings.shapeSelected,
                    boardCardsCornerRadius = settings.boardCardsCornerRadius,
                    boardCardsThickness = settings.boardCardsThickness,
                    boardCardsElevation = settings.boardCardsElevation,
                    boardCardsShadow = settings.boardCardsShadow,
                    boardCardsBorderColor = Color(settings.boardCardsBorderColor.toLong(16)),
                    isRGBEffectOn = settings.isRGBEffectOn,
                    isGradientOn = settings.isGradientOn,
                    profilePictureBitmap = settings.profilePicture?.decodeToImageBitmap(),
                )
            }
            backgroundMusic(
                isBackgroundMusicOn = settings.isBackgroundMusicOn,
                isAppInOnStop = false,
            )
            onSaveImage()
            this.cancel()
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            SettingsEvent.ToggleIsBackgroundMusicOn -> {
                _state.update { it.copy(isBackgroundMusicOn = !it.isBackgroundMusicOn) }

                viewModelScope.launch(Dispatchers.IO) {
                    val settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isBackgroundMusicOn = state.value.isBackgroundMusicOn
                    repository.setAllSettingsValues(settings)
                }
                backgroundMusic(
                    isBackgroundMusicOn = state.value.isBackgroundMusicOn,
                    isAppInOnStop = false,
                )
            }

            SettingsEvent.ToggleIsDarkThemeOn -> {
                _state.update { it.copy(isDarkThemeOn = !it.isDarkThemeOn) }

                viewModelScope.launch(Dispatchers.IO) {
                    val settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isDarkThemeOn = state.value.isDarkThemeOn
                    repository.setAllSettingsValues(settings)
                }
            }

            SettingsEvent.ToggleIsDynamicColorsOn -> {
                _state.update { it.copy(isDynamicColorsOn = !it.isDynamicColorsOn) }

                viewModelScope.launch(Dispatchers.IO) {
                    val settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isDynamicColorsOn = state.value.isDynamicColorsOn
                    repository.setAllSettingsValues(settings)
                }
            }

            is SettingsEvent.ToggleIsFollowingSystemOn -> {
                _state.update {
                    it.copy(
                        isFollowingSystemOn = !it.isFollowingSystemOn,
                        isDarkThemeOn = event.isSystemInDarkTheme,
                    )
                }

                viewModelScope.launch(Dispatchers.IO) {
                    val settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isFollowingSystemOn = state.value.isFollowingSystemOn
                    settings.isDarkThemeOn = event.isSystemInDarkTheme

                    repository.setAllSettingsValues(settings)
                }
            }

            SettingsEvent.ToggleIsSoundEffectsOn -> {
                _state.update { it.copy(isSoundEffectsOn = !it.isSoundEffectsOn) }

                viewModelScope.launch(Dispatchers.IO) {
                    val settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isSoundEffectsOn = state.value.isSoundEffectsOn
                    repository.setAllSettingsValues(settings)
                }
            }

            is SettingsEvent.SetIsDarkThemeOn -> {
                _state.update { it.copy(isDarkThemeOn = event.value) }

                viewModelScope.launch(Dispatchers.IO) {
                    val settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.isDarkThemeOn = state.value.isDarkThemeOn
                    repository.setAllSettingsValues(settings)
                }
            }

            SettingsEvent.OnSettingsScreenDestroy -> {
                _state.update {
                    it.copy(
                        isAboutShown = false,
                        showColorSelectionDialog = false,
                    )
                }
            }

            is SettingsEvent.StartBackgroundMusic -> {
                backgroundMusic(
                    isBackgroundMusicOn = state.value.isBackgroundMusicOn,
                    isAppInOnStop = event.isAppInOnStop,
                )
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
                    val settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.selectedWinnerLineColor = event.value
                    repository.setAllSettingsValues(settings)
                }
                _state.update { it.copy(selectedWinnerLineColor = event.value) }
            }

            is SettingsEvent.SelectedXColor -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.selectedXColor = event.value
                    repository.setAllSettingsValues(settings)
                }
                _state.update { it.copy(selectedXColor = event.value) }
            }

            is SettingsEvent.SelectedCircleColor -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.selectedCircleColor = event.value
                    repository.setAllSettingsValues(settings)
                }
                _state.update { it.copy(selectedCircleColor = event.value) }
            }

            SettingsEvent.HideWhatsNewDialog -> {
                _state.update { it.copy(showWhatsNewDialog = false) }
                viewModelScope.launch(Dispatchers.IO) {
                    val settings = repository.getAllSettingsValues(1) ?: defaultSettings
                    settings.appVersion = VERSION_NAME
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
                    val settings = repository.getAllSettingsValues(1) ?: defaultSettings
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
                profilePictureBitmap = null,
                showProfilePictureMenu = false,
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            val settings = repository.getAllSettingsValues(1) ?: defaultSettings
            settings.profilePicture = null
            repository.setAllSettingsValues(settings)
        }
    }

    // An alternative to get images from gallery by using Uris, for use only in Android
    fun onSaveImage(imageUri: Uri? = null) {
        val profileManager = ProfilePictureUtilsImpl()
        _state.update { it.copy(showProfilePictureMenu = false) }

        viewModelScope.launch(Dispatchers.IO) {
            imageUri?.let {
                val settings = repository.getAllSettingsValues(1) ?: defaultSettings
                settings.profilePicture = profileManager.getPictureBitmap(imageUri).toByteArray()
                repository.setAllSettingsValues(settings)
                _state.update {
                    it.copy(profilePictureBitmap = settings.profilePicture?.decodeToImageBitmap())
                }
            }
        }
    }

    fun onSaveImageByBitmap(imageBitmap: ImageBitmap?) {
        _state.update { it.copy(showProfilePictureMenu = false) }

        viewModelScope.launch(Dispatchers.IO) {
            imageBitmap?.let { bitmap ->
                _state.update {
                    it.copy(profilePictureBitmap = bitmap)
                }
                val settings = repository.getAllSettingsValues(1) ?: defaultSettings
                settings.profilePicture = bitmap.toByteArray()
                repository.setAllSettingsValues(settings)
            }
        }
    }
}
