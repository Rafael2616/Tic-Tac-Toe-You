package com.rafael.tictactoe.settings.settingsScreen.domain.util

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.SettingsGroup

class ComponentValidator {

    fun validateAboutAppSubComp(groupType: SettingsGroup, index: Int, isAboutShown: Boolean): Boolean {
        if (groupType == SettingsGroup.Others && index == 0 && isAboutShown) {
            return true
        }
        return false
    }

    fun validateCard(groupType: SettingsGroup, index: Int, isFollowingSystemOn: Boolean): Boolean {
        val isDynamicColorsSupported = VERSION.SDK_INT >= VERSION_CODES.S

        if (groupType == SettingsGroup.Theme && (
                (index == 1 && !isFollowingSystemOn) ||
                    (index == 2 && isDynamicColorsSupported) ||
                    (index != 1 && index != 2)
                )
        ) {
            return true
        }
        if (groupType != SettingsGroup.Theme) {
            return true
        }
        return false
    }

    fun validateCanvas(groupType: SettingsGroup): Boolean {
        if (groupType == SettingsGroup.Customization) {
            return true
        }
        return false
    }

    fun validateDivider(groupType: SettingsGroup, index: Int, isFollowingSystemOn: Boolean): Boolean {
        val isDynamicColorsSupported = VERSION.SDK_INT >= VERSION_CODES.S

        if (groupType == SettingsGroup.Theme && (
                (index == 0 && isFollowingSystemOn && !isDynamicColorsSupported) ||
                    (index == 1 && !isDynamicColorsSupported)
                )
        ) {
            return false
        } else {
            return true
        }
    }
}

@Composable
fun rememberComponentValidator(): ComponentValidator {
    return remember { ComponentValidator() }
}
