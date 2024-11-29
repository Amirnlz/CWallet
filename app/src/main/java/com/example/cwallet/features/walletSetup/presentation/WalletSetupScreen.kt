package com.example.cwallet.features.walletSetup.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun WalletSetupScreen(
    modifier: Modifier = Modifier,
    onNavigateToRecoverWallet: () -> Unit,
    onNavigateToCreateWallet: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = "Welcome to Your Crypto Wallet",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(

            onClick = onNavigateToRecoverWallet,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
        ) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Recover Wallet")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Recover Wallet")
        }
        Button(
            onClick = onNavigateToCreateWallet,
            modifier = Modifier.fillMaxWidth(),

            ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Create New Wallet")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Create New Wallet")
        }
    }
}

