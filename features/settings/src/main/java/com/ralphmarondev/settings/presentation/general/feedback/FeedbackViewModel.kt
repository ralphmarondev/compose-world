package com.ralphmarondev.settings.presentation.general.feedback

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FeedbackViewModel : ViewModel() {
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> get() = _name

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> get() = _email

    private val _feedback = MutableStateFlow("")
    val feedback: StateFlow<String> get() = _feedback

    fun sendFeedback() {
        // TODO: Implement this!
        // TODO: Open email passing all of the arguments ready to be send.
        Log.d(
            "FEEDBACK",
            "Name: ${_name.value}, Email: ${_email.value}, Feedback: ${_feedback.value}"
        )

        _name.value = ""
        _email.value = ""
        _feedback.value = ""
    }

    fun onNameChange(value: String) {
        _name.value = value
    }

    fun onEmailChange(value: String) {
        _email.value = value
    }

    fun onFeedbackChange(value: String) {
        _feedback.value = value
    }
}