package com.sunil.arch.navigation

import androidx.navigation.NavDirections

/*navigation from a [ViewModel]*/

sealed class NavigationController{
    data class To(val directions: NavDirections): NavigationController()
    object Back: NavigationController()
}