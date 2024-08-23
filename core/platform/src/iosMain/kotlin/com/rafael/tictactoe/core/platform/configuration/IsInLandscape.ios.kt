package com.rafael.tictactoe.core.platform.configuration

import platform.UIKit.UIDevice
import platform.UIKit.UIDeviceOrientation

actual val isInLandscape: Boolean
    get() = UIDevice.currentDevice.orientation ==
        (UIDeviceOrientation.UIDeviceOrientationLandscapeLeft || UIDeviceOrientation.UIDeviceOrientationLandscapeRight)
