package com.ralphmarondev.onboarding.domain.usecases

import com.ralphmarondev.data.user.User
import com.ralphmarondev.onboarding.domain.repository.OnboardingRepository

class RegisterUserUseCase(private val repository: OnboardingRepository) {
    suspend fun registerUser(user: User, response: (Boolean, String?) -> Unit) {
        try {
            repository.register(user)
            response(true, null)
        } catch (e: Exception) {
            response(false, e.message)
        }
    }
}