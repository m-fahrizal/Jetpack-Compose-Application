package com.example.submissionjetpack.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object DetailIndomie : Screen("home/{indomieId}") {
        fun createRoute(indomieId: Long) = "home/$indomieId"
    }
}
