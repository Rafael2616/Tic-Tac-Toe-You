package com.rafael.tictactoe.feature.settings.domain.profile

import com.rafael.tictactoe.feature.settings.data.profile.AndroidProfilePictureUtils

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class ProfilePictureUtilsImpl : ProfilePictureUtils by AndroidProfilePictureUtils()
