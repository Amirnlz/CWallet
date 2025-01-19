package com.amirnlz.cwallet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amirnlz.onboarding.presentation.screens.OnboardingScreen
import com.amirnlz.wallet_creation.presentation.screen.WalletCreationScreen
import com.amirnlz.wallet_creation.presentation.screen.WalletCreationViewModel
import com.amirnlz.wallet_creation.presentation.screen.WalletWordsValidationScreen
import com.amirnlz.wallet_recovery.presentation.screen.WalletRecoveryScreen


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
            WalletRecoveryScreen(modifier = Modifier)
        }
        composable<Screen.WalletCreation> {
            val viewModel = hiltViewModel<WalletCreationViewModel>()
            WalletCreationScreen(
                modifier = Modifier,
                viewModel = viewModel,
                navigateValidationScreen = {
                    navHostController.navigate(Screen.WalletWordsValidation)
                }
            )
        }
        composable<Screen.WalletWordsValidation> {
            val viewModel = hiltViewModel<WalletCreationViewModel>()
            WalletWordsValidationScreen(
                modifier = Modifier,
                viewModel = viewModel,
                navigate = {}
            )
        }
    }
}