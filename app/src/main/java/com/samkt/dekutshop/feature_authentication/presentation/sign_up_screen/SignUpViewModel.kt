package com.samkt.dekutshop.feature_authentication.presentation.sign_up_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception


const val TAG = "Authentication"

class SignUpViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()


    var email by mutableStateOf("")
        private set

    var confirmationMessage:String? by mutableStateOf(null)

    var password by mutableStateOf("")

    var showVerified by mutableStateOf(false)
    fun onEmailChange(value: String) {
        email = value
    }

    fun onPasswordChange(value: String){
        password = value
    }

    fun signUpWithEmailAndPassword(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    val result = auth.createUserWithEmailAndPassword(email, password).await()
                    result.user?.sendEmailVerification()?.await()
                    confirmationMessage = "Account successfully created.Check your email for verification"
                    showVerified = true
                } catch (e: Exception) {
                    confirmationMessage = e.message
                    Log.d(TAG, e.message ?: "")
                }
            }
        }
    }

    fun signInWithEmailAndPassword(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    val user = auth.signInWithEmailAndPassword(email, password).await().user
                    confirmationMessage = if (user?.isEmailVerified == true){
                        "Login successful"
                    }else{
                        "Email is not verified"
                    }
                }catch (e:Exception){
                    Log.d(TAG,e.message ?: "Failed to sign In")
                }
            }
        }
    }

}

class FailedAuthException(message:String):Exception(message)

