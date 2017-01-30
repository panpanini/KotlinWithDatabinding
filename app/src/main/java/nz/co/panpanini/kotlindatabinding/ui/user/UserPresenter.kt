package nz.co.panpanini.kotlindatabinding.ui.user

import android.content.Intent
import com.github.ajalt.timberkt.d
import nz.co.panpanini.kotlindatabinding.repository.SharedPreferencesUserRepository
import nz.co.panpanini.kotlindatabinding.repository.UserRepository
import rx.android.schedulers.AndroidSchedulers
import timber.log.Timber

/**
 * Created by matthewvern on 2017/01/27.
 */
// we can define a val with a default value in the constructor. this will create us two constructors:
// first is UserPresenter(userRepository: UserRepository) and second is UserPresenter()
// in the second one, it will just call UserPresenter(SharedPreferencesUserRepository())
// TODO: defaults
class UserPresenter(val view: UserView, val userRepository: UserRepository) {


    fun onSearchClick(username: String) {
        /*
        Two things here:
        1) this is Java equivalent to Timber.d("searching for " + username);
        2) you can escape variables in Kotlin strings by using $variableName
            a) you can escape arbitrary code by using ${ methodCall() }
         */
        d { "searching for $username" }
        userRepository.getUser(username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user -> // this names the variable to be used in the lambda as 'user'
                    d {
                        "$user"
                    }
                    user?.let {
                        view.bindUser(user)
                        return@subscribe // return to the end of subscribe lambda, rather than just let
                    }

                    // if we've got this far, it must be an error
                    view.networkError("Unable to bind user to view")
                },
                { error ->
                    view.networkError(error.message?: "network")

                })
    }


    fun onReposClicked(username: String) {
        view.openRepoView(username)
    }

}