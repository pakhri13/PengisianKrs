package net.simplifiedcoding.ui.Admin

import android.view.View
import androidx.lifecycle.ViewModel
import net.simplifiedcoding.data.repositories.UserRepository
import net.simplifiedcoding.utils.startLoginActivity

class AdminViewModel  (
    private val repository: UserRepository
) : ViewModel() {
    fun logout(view: View){
        repository.logout()
        view.context.startLoginActivity()
    }
}