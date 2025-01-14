package com.amirnlz.cwallet.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amirnlz.onboarding.presentation.screens.OnboardingScreen
import com.amirnlz.wallet_creation.presentation.screen.WalletCreationScreen
import com.amirnlz.wallet_recovery.presentation.screen.WalletRecoveryScreen


@Composable
fun AppNavigation(modifier: Modifier = Modifier, navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Onboarding,
        modifier = modifier.padding(16.dp)
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
            WalletRecoveryScreen(modifier = Modifier)
        }
        composable<Screen.WalletCreation> {
            WalletCreationScreen(modifier = Modifier)
        }

    }

}