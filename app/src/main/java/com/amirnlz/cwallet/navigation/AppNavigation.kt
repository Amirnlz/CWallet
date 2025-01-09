package com.amirnlz.cwallet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amirnlz.onboarding.presentation.screens.OnboardingScreen


@Composable
fun AppNavigation(modifier: Modifier = Modifier, navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Onboarding,
        modifier = modifier
    ) {
        composable<Screen.Onboarding> {
            OnboardingScreen(
                modifier = Modifier,
                navigateToRecoverScreen = {
                    navHostController.navigate(Screen.WalletRecovery)
                },
                navigateToGenerateScreen = {
                    navHostController.navigate(Screen.WalletCreation)
                }

            )
        }
        composable<Screen.WalletRecovery> {
        }
        composable<Screen.WalletCreation> {
        }

    }

}