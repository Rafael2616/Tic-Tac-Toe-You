package com.rafael.tictactoe.settings.libraryScreen.presentation

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.libraryScreen.presentation.utils.annotatedLink
import com.rafael.tictactoe.settings.libraryScreen.presentation.viewmodels.LibraryViewModel

@Composable
@ExperimentalMaterial3Api
fun LibraryScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LibraryViewModel = hiltViewModel(),
) {
    val uriHandler = LocalUriHandler.current
    val layoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var isGoBackClicked by rememberSaveable { mutableStateOf(false) }
    val libraries by viewModel.ossLibraries.collectAsState()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(stringResource(R.string.open_source_licenses))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (!isGoBackClicked) {
                                isGoBackClicked = true
                                navController.popBackStack()
                            }
                        },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go Back",
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(paddingValues),
        ) {
            itemsIndexed(libraries) { index, library ->
                val licenseLink = (library.spdxLicenses ?: library.unknownLicenses)?.firstOrNull()?.url.orEmpty()
                val annotatedLicenseLink = annotatedLink(licenseLink)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = library.name,
                    )
                    Text(
                        text = annotatedLicenseLink,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp,
                        ),
                        modifier = Modifier
                            .pointerInput(Unit) {
                                detectTapGestures { pos ->
                                    layoutResult.value?.let { layoutResult ->
                                        val position = layoutResult.getOffsetForPosition(pos)
                                        val aLicenseLink = annotatedLicenseLink.getStringAnnotations(position, position).firstOrNull()
                                        if (aLicenseLink?.tag == "URL") {
                                            uriHandler.openUri(aLicenseLink.item)
                                        }
                                    }
                                }
                            },
                        onTextLayout = {
                            layoutResult.value = it
                        },
                    )
                }
                if (index < (libraries.size - 1)) {
                    HorizontalDivider(
                        thickness = 0.5.dp,
                        color = Color.Gray,
                    )
                }
            }
        }
    }
}
