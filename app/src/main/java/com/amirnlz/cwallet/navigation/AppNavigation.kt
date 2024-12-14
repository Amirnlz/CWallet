package com.amirnlz.cwallet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amirnlz.onboarding.presentation.screens.recovery.MnemonicPhraseRecoveryScreen
import com.amirnlz.onboarding.presentation.screens.welcome.WelcomeScreen


@Composable
fun AppNavigation(modifier: Modifier = Modifier, navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = Screen.Welcome,
        modifier = modifier
    ) {
        composable<Screen.Welcome> {
            WelcomeScreen(
                modifier = Modifier,
                navigateToRecoverScreen = {
                    navHostController.navigate(Screen.MnemonicPhraseRecovery)
                },
                navigateToGenerateScreen = {
//                    navHostController.navigate(Screen.MnemonicGeneration)
                }

            )
        }
        composable<Screen.MnemonicPhraseRecovery> {
            MnemonicPhraseRecoveryScreen()
        }
//        composable<Screen.MnemonicGeneration> {
//            MnemonicPhraseGenerationScreen(viewModel = ) {
//
//            }
//        }

    }

}