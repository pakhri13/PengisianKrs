package net.simplifiedcoding.ui.Admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.simplifiedcoding.data.repositories.UserRepository
import net.simplifiedcoding.ui.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class AdminViewModelFactory (
    private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AdminViewModel(repository) as T
    }

}