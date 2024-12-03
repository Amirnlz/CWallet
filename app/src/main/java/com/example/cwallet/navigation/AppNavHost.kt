package com.example.cwallet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cwallet.features.walletSetup.presentation.MnemonicScreen
import com.example.cwallet.features.walletSetup.presentation.MnemonicValidationScreen
import com.example.cwallet.features.walletSetup.presentation.MnemonicViewModel
import com.example.cwallet.features.walletSetup.presentation.ValidateMnemonicViewModel
import com.example.cwallet.features.walletSetup.presentation.WalletSetupScreen


@Composable
fun AppNavHost(navHostController: NavHostController, modifier: Modifier = Modifier) {
    val validateViewModel = hiltViewModel<ValidateMnemonicViewModel>()

    NavHost(
        navController = navHostController,
        startDestination = Screen.SetupScreen,
        modifier = modifier
    ) {
        composable<Screen.SetupScreen> {
            WalletSetupScreen(
                modifier,
                onNavigateToCreateWallet = { navHostController.navigate(Screen.GenerateMnemonicScreen) },
                onNavigateToRecoverWallet = { navHostController.navigate(Screen.ValidateMnemonicScreen) }
            )
        }
        composable<Screen.GenerateMnemonicScreen> {
            val viewModel = hiltViewModel<MnemonicViewModel>()
            MnemonicScreen(
                viewModel = viewModel,
                modifier = modifier,
                onNavigateValidateMnemonic = {
                    navHostController.navigate(Screen.ValidateMnemonicScreen)
                    validateViewModel.setCorrectMnemonic(viewModel.mnemonic.value)
                }
            )
        }
        composable<Screen.ValidateMnemonicScreen> {
            MnemonicValidationScreen(
                modifier,
                viewModel = validateViewModel,
                onValidationComplete = {
                },
            )
        }
    }
}