package net.simplifiedcoding.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import net.simplifiedcoding.data.repositories.UserRepository

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    //email and password for the input
    var email: String? = null
    var password: String? = null
    var name: String? = null
    var npm: String? = null

    //auth listener
    var authListener: AuthListener? = null


    //disposable to dispose the Completable
    private val disposables = CompositeDisposable()

    val user by lazy {
        repository.currentUser()
    }

    //function to perform login
    fun login() {

        //validating email and password
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }
        //authentication started
        authListener?.onStarted()

        if (email.equals("admin@mail.com") || password.equals("admin123")) {
            val disposable = repository.login(email!!, password!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //sending a success callback
                    authListener?.onSuccessAdmin()
                }, {
                    //sending a failure callback
                    authListener?.onFailure(it.message!!)
                })
            disposables.add(disposable)
        } else {
            val disposable = repository.login(email!!, password!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //sending a success callback
                    authListener?.onSuccess()
                }, {
                    //sending a failure callback
                    authListener?.onFailure(it.message!!)
                })
            disposables.add(disposable)
        }


        //calling login from repository to perform the actual authentication

    }

    //Doing same thing with signup
    fun signup() {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Please input all values")
            return
        }
        authListener?.onStarted()
        val disposable = repository.register(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }

    fun goToSignup(view: View) {
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun goToLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


    //disposing the disposables
    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}