package net.simplifiedcoding.ui.home

import android.view.View
import androidx.lifecycle.ViewModel
import net.simplifiedcoding.data.repositories.UserRepository
import net.simplifiedcoding.utils.startJurusanActivity
import net.simplifiedcoding.utils.startLoginActivity
import net.simplifiedcoding.utils.startProfilActivity

class HomeViewModel(
    private val repository: UserRepository
) : ViewModel() {

    val user by lazy {
        repository.currentUser()
    }
    
    fun logout(view: View){
        repository.logout()
        view.context.startLoginActivity()
    }

    fun jurusan(view: View){
        view.context.startJurusanActivity()
    }

    fun profil(view: View){
        view.context.startProfilActivity()
    }
}