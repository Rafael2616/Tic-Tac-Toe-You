package com.rafael.tictactoe.feature.libraryinfo

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import com.rafael.tictactoe.feature.libraryinfo.domain.model.OssLibrary
import com.rafael.tictactoe.feature.libraryinfo.presentation.LibraryScreen
import com.rafael.tictactoe.feature.libraryinfo.presentation.viewmodel.LibraryViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.assertNotNull
import org.junit.Test

class LibraryScreenTests {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testLibraryDataLoadingAndDisplay() = runComposeUiTest {
        val viewModel = mockk<LibraryViewModel>()
        val libraries = listOf(
            OssLibrary("Library A", "ActifactId", "1.0.0", "MIT License"),
            OssLibrary("Library B", "ActifactId", "2.2.1", "Apache License 2.0"),
        )
        val librariesFlow = MutableStateFlow(libraries)

        every { viewModel.ossLibraries } returns librariesFlow

        setContent {
            LibraryScreen(emptyList(), { })
        }

        val libraryAName = onNodeWithText("Library A")
        val libraryBVersion = onNodeWithText("v2.2.1")
        val libraryBLicense = onNodeWithText("Apache License 2.0")

        libraryAName.assertIsDisplayed()
        libraryBVersion.assertIsDisplayed()
        libraryBLicense.assertIsDisplayed()

        assertNotNull(libraryAName)
        assertNotNull(libraryBVersion)
        assertNotNull(libraryBLicense)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testLibraryScreenGoBackButtonClick() = runComposeUiTest {
        setContent {
            LibraryScreen(emptyList(), {})
        }

        val goBackButton = onNodeWithContentDescription("Go Back")
        goBackButton.assertIsDisplayed()
        goBackButton.performClick()
    }
}
