package com.ralphmarondev.onboarding.data.repository

import com.ralphmarondev.data.user.User
import com.ralphmarondev.data.user.UserDao
import com.ralphmarondev.onboarding.domain.repository.OnboardingRepository

class OnboardingRepositoryImpl(
    private val dao: UserDao
) : OnboardingRepository {
    override suspend fun register(user: User) {
        dao.register(user)
    }
}