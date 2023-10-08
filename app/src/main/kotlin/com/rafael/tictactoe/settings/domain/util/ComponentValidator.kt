package com.rafael.tictactoe.settings.domain.util

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES

class ComponentValidator {

    fun validateFirstIcon(groupType: GroupType): Boolean {
        if (groupType == GroupType.CUSTOMIZATION) {
            return false
        }
        if (groupType == GroupType.THEME) {
            return false
        }   
        return true
    }

    fun validateImage(groupType: GroupType): Boolean {
        if (groupType == GroupType.THEME) {
            return true
        }
        return false
    }

    fun validateSubText(groupType: GroupType, index: Int, isAboutShown: Boolean): Boolean {
        if (groupType == GroupType.OTHER && index == 0 && isAboutShown) {
            return true
        }
        return false
    }
    
    fun validateCard(groupType: GroupType, index: Int, isFollowingSystemOn: Boolean): Boolean {
        val isDynamicColorsSupported = VERSION.SDK_INT >= VERSION_CODES.S

        if (groupType == GroupType.THEME && ((index == 1 && !isFollowingSystemOn) ||
        (index == 2 && isDynamicColorsSupported) ||
        (index != 1 && index != 2))) {
            return true
        }
        if (groupType != GroupType.THEME) {
            return true
        }
        if (groupType == GroupType.THEME && ((index == 1 && isFollowingSystemOn) ||
        (index == 2 && !isDynamicColorsSupported))) {
            return false
        }
        return false
    }
    
    fun validateCanvas(groupType: GroupType): Boolean {
        if (groupType == GroupType.CUSTOMIZATION) {
            return true
        }
        return false
    }

    fun validateDivider(groupType: GroupType, index: Int, isFollowingSystemOn: Boolean): Boolean {
        val isDynamicColorsSupported = VERSION.SDK_INT >= VERSION_CODES.S

        if (groupType == GroupType.THEME && ((index == 0 && isFollowingSystemOn) ||
        (index == 1 && !isDynamicColorsSupported))) {
            return false
        } else {
            return true
        }
    }
}