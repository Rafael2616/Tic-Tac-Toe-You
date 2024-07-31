package com.rafael.tictactoe

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rafael.tictactoe.settings.domain.model.OssLibrary
import com.rafael.tictactoe.settings.libraryScreen.presentation.LibraryScreen
import com.rafael.tictactoe.settings.libraryScreen.presentation.viewmodel.LibraryViewModel
import com.rafael.tictactoe.settings.settingsScreen.presentation.viewmodel.SettingsViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LibraryScreenTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLibraryDataLoadingAndDisplay() {
        val navController = mockk<NavController>()
        val viewModel = mockk<LibraryViewModel>()
        val libraries = listOf(
            OssLibrary("Library A", "ActifactId", "1.0.0", "MIT License"),
            OssLibrary("Library B", "ActifactId", "2.2.1", "Apache License 2.0"),
        )
        val librariesFlow = MutableStateFlow(libraries)

        every { viewModel.ossLibraries } returns librariesFlow

        composeTestRule.setContent {
            LibraryScreen(navController, viewModel = viewModel)
        }

        val libraryAName = composeTestRule.onNodeWithText("Library A")
        val libraryBVersion = composeTestRule.onNodeWithText("v2.2.1")
        val libraryBLicense = composeTestRule.onNodeWithText("Apache License 2.0")

        libraryAName.assertIsDisplayed()
        libraryBVersion.assertIsDisplayed()
        libraryBLicense.assertIsDisplayed()

        assertNotNull(libraryAName)
        assertNotNull(libraryBVersion)
        assertNotNull(libraryBLicense)
    }

    @Test
    fun testLibraryScreenGoBackButtonClick() {
        val navController = mockk<NavController>()
        val viewModel = mockk<LibraryViewModel>()
        val settingsVM = mockk<SettingsViewModel>()

        composeTestRule.setContent {
            LibraryScreen(navController, viewModel = viewModel, settingsVM = settingsVM)
        }

        val goBackButton = composeTestRule.onNodeWithContentDescription("Go Back")
        goBackButton.assertIsDisplayed()
        goBackButton.performClick()

        verify { navController.popBackStack() }
        verify { settingsVM.execUIClickSound() }
    }
}
