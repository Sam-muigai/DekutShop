package com.samkt.dekutshop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.samkt.dekutshop.feature_authentication.presentation.sign_up_screen.SignUpScreen
import com.samkt.dekutshop.feature_authentication.presentation.sign_up_screen.SignUpViewModel
import com.samkt.dekutshop.feature_authentication.presentation.sign_up_screen.TAG
import com.samkt.dekutshop.ui.theme.DekutShopTheme


class MainActivity : ComponentActivity() {

    private val signUpViewModel by viewModels<SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val data = intent.data

        if (data != null){
           // signUpViewModel.checkLogin(emailLink = data.toString())
            Log.d(TAG,data.toString())
        }else{
            Log.d(TAG,"No data")
        }

        setContent {
            DekutShopTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "sign_up"
    ) {
        composable("sign_up") {
            SignUpScreen()
        }
        composable(
            route = "detail",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://dekutshop.page.link/?link={link}"
                    action = Intent.ACTION_VIEW
                }
            ),
            arguments = listOf(
                navArgument("link") {
                    type = NavType.StringType
                    defaultValue = -1
                }
            )
        ) { entry ->
            val id = entry.arguments?.getInt("link")
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = id.toString())
            }
        }
    }
}























