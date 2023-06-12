package com.rafael.tictactoe.common.presentation.ui.theme

import android.content.Context
import android.os.Build.*
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.*
import androidx.compose.runtime.livedata.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.*
//import androidx.core.view.WindowCompat
import androidx.lifecycle.*
import com.google.accompanist.systemuicontroller.*
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import javax.inject.Inject
import kotlinx.coroutines.*


private val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)

private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)


@Composable
fun AppTheme(
context: Context,
content: @Composable() () -> Unit
) {

    //val isDarkThemeOn by viewModel.isDarkThemeOn.collectAsState()
    val isDynamicColorsSupported = VERSION.SDK_INT >= VERSION_CODES.S 
    var isDynamicColorsOn by rememberSaveable { mutableStateOf(true)}
    var isFollowingSystemOn by rememberSaveable { mutableStateOf(true)}
    var isDarkThemeOn by rememberSaveable { mutableStateOf(true)}
    var isSystemInDarkTheme: Boolean = isSystemInDarkTheme()

	if ((isFollowingSystemOn) && (isSystemInDarkTheme)) {
		isDarkThemeOn = true
	}
	if ((isFollowingSystemOn) && (!isSystemInDarkTheme)) {
		isDarkThemeOn = false
	}

	val colors = if (!isFollowingSystemOn) {
  	  if (isDynamicColorsSupported && isDynamicColorsOn) {
      	  if (!isDarkThemeOn) {
       	     dynamicLightColorScheme(context)
 	   } else {
     	       dynamicDarkColorScheme(context)
      	  }
  	  } else {
     	   if (!isDarkThemeOn) {
     	       LightColors
    	    } else {
     	       DarkColors
       	 }
  	  }
	} else {
  	DarkColors
	}

	MaterialTheme(
  	  colorScheme = colors,
  	  shapes = Shapes,
  	  typography = Typography,
   	 content = content,
	)

	val systemUiController = rememberSystemUiController()
    if ((isDarkThemeOn || isSystemInDarkTheme) && (isFollowingSystemOn)) {
        systemUiController.setSystemBarsColor(
 	   color = Color(0xFF1C1B1F),
	    darkIcons = false,
       )
   } else {
        systemUiController.setSystemBarsColor(
	    color = Color(0xFFFFFBFE),
	    darkIcons = true,
        )
	}
}

/*
val colors = if (!isSystemThemeOn) {
    if (isDynamicColorSupported && isDynamicColorsOn) {
        if (!isDarkThemeOn) {
            dynamicLightColorScheme(context)
    } else {
            dynamicDarkColorScheme(context)
        }
    } else {
        if (!isDarkThemeOn) {
            LightColors
        } else {
            DarkColors
        }
    }
} else {
    if (dynamic && isDynamicColorsOn) {
        if (!isSystemDarkTheme) {
            dynamicLightColorScheme(context)
        } else {
            dynamicDarkColorScheme(context)
        }
    } else {
        if (!isSystemDarkTheme) {
            LightColors
        } else {
            DarkColors
        }
    }
}
*/
/*
@Composable
fun MyComposeApplicationTheme(darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
                              dynamicColor: Boolean = true,
                              content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(
                context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window,
                view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(colorScheme = colorScheme, typography = Typography,
        content = content)
}*/