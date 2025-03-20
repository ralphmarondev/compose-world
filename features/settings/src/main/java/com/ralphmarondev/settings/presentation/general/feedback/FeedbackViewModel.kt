package com.ralphmarondev.settings.presentation.general.feedback

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FeedbackViewModel : ViewModel() {
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _feedback = MutableStateFlow("")
    val feedback = _feedback.asStateFlow()

    fun sendFeedback(
        context: Context
    ) {
        Log.d(
            "FEEDBACK",
            "Name: ${_name.value}, Email: ${_email.value}, Feedback: ${_feedback.value}"
        )

        val recipient = "edaralphmaron@gmail.com"
        val subject = "Feedback for Compose World from ${_name.value}"
        val body = """
            Name: ${_name.value}
            Email: ${_email.value}
            
            Feedback:
            ${_feedback.value}
        """.trimIndent()

        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }

        try {
            context.startActivity(Intent.createChooser(emailIntent, "Send Feedback via"))
        } catch (e: Exception) {
            Log.e("FEEDBACK", "Failed to open email client: ${e.message}")
        }

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