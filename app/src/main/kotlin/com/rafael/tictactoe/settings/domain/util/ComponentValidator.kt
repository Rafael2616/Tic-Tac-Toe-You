package com.rafael.tictactoe.settings.domain.util

class ComponentValidator {

    fun validateImage(groupType: GroupType, index: Int): Boolean {
        if (groupType != GroupType.CUSTOMIZATION) {
            return true
        }
        if (groupType == GroupType.CUSTOMIZATION && index == 0) {
            return false
        }
        if (groupType == GroupType.CUSTOMIZATION && index != 0) {
            return true
        }
        return false
    }
    
    fun validateCardWithPreview(groupType: GroupType, index: Int): Boolean {
        if (groupType == GroupType.CUSTOMIZATION && index == 0) {
             return true   
        }
        return false
    }
    
    fun validateSubText(groupType: GroupType, index: Int, isAboutShown: Boolean) {
        if (groupType == GroupType.OTHER && index == 0 && isAboutShown) {
            return true
        }
        return false
    }
}