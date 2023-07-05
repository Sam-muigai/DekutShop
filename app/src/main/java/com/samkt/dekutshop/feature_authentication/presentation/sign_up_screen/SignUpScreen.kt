package com.samkt.dekutshop.feature_authentication.presentation.sign_up_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SignUpScreen(viewModel: SignUpViewModel = viewModel()) {

    SignUpScreenContent(
        value = viewModel.email,
        password = viewModel.password,
        confirmationMessage = viewModel.confirmationMessage,
        onLoginClicked = viewModel::signInWithEmailAndPassword,
        onClick = viewModel::signUpWithEmailAndPassword,
        verifiedButton = viewModel.showVerified,
        onValueChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreenContent(
    modifier: Modifier = Modifier,
    value: String,
    password: String,
    confirmationMessage: String?,
    onClick: () -> Unit,
    onLoginClicked: () -> Unit = {},
    verifiedButton: Boolean = false,
    onValueChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        confirmationMessage?.let {
            Text(text = it)
        }
        AnimatedVisibility(visible = verifiedButton) {
            Button(
                onClick = {
                    onLoginClicked.invoke()
                }
            ) {
                Text(text = "VERIFIED")
            }
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            value = value,
            shape = RoundedCornerShape(100),
            singleLine = true,
            onValueChange = onValueChange,
            placeholder = {
                Text(text = "Enter your Email")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            value = password,
            shape = RoundedCornerShape(100),
            singleLine = true,
            onValueChange = onPasswordChange,
            placeholder = {
                Text(text = "Enter password.")
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onClick
        ) {
            Text(text = "SIGN UP")
        }
    }

}