package com.rafael.tictactoe.core.navigation.impl

import com.rafael.tictactoe.core.navigation.Destinations as RealDestinations

sealed class Destinations(val screen: String) = RealDestinations(screen)
    