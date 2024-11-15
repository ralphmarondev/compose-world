package com.ralphmarondev.keepr.presentation.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.keepr.data.local.KeeprDao
import com.ralphmarondev.keepr.data.repository.KeeprRepositoryImpl
import com.ralphmarondev.keepr.domain.model.Account
import com.ralphmarondev.keepr.domain.usecases.CreateNewAccountUseCase
import com.ralphmarondev.keepr.domain.usecases.GetAccountsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModelFactory(
    private val keeprDao: KeeprDao,
    private val subCategory: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(keeprDao, subCategory) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class DetailViewModel(
    private val keeprDao: KeeprDao,
    private val subCategory: String
) : ViewModel() {

    private val repository = KeeprRepositoryImpl(keeprDao)
    private val getAccountsUseCase = GetAccountsUseCase(repository)
    private val createNewAccountUseCase = CreateNewAccountUseCase(repository)

    private val _accounts = MutableStateFlow<List<Account>>(emptyList())
    val accounts: StateFlow<List<Account>> get() = _accounts

    private val _showNewAccount = MutableStateFlow(false)
    val showNewAccount: StateFlow<Boolean> get() = _showNewAccount

    init {
        fetchAccounts()
    }

    private fun fetchAccounts() {
        viewModelScope.launch {
            try {
                getAccountsUseCase(subCategory).collect { accountList ->
                    _accounts.value = accountList
                }
                Log.d("DetailViewModel", "Fetching accounts successful.")
            } catch (e: Exception) {
                Log.e("DetailViewModel", "Error fetching accounts: ${e.message}")
            }
        }
    }

    fun toggleShowNewAccount() {
        _showNewAccount.value = !_showNewAccount.value
    }

    fun createNewAccount(
        name: String,
        username: String,
        password: String,
        response: (Boolean, String?) -> Unit
    ) {
        viewModelScope.launch {
            val account = Account(
                name = name,
                username = username,
                password = password,
                subCategoryName = subCategory
            )
            createNewAccountUseCase.createAccount(
                account = account,
                response = response
            )
        }
    }
}