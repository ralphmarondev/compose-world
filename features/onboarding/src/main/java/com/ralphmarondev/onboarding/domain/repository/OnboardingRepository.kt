package com.ralphmarondev.onboarding.domain.repository

import com.ralphmarondev.data.user.User

interface OnboardingRepository {
    suspend fun register(user: User)
}