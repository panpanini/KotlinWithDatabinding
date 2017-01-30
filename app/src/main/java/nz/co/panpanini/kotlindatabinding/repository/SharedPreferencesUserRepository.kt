package nz.co.panpanini.kotlindatabinding.repository

import nz.co.panpanini.kotlindatabinding.api.GitHub
import nz.co.panpanini.kotlindatabinding.model.User
import rx.Observable

/**
 * Created by matthewvern on 2017/01/27.
 */
class SharedPreferencesUserRepository(gitHub: GitHub): UserRepository(gitHub) {

    override fun getUser(username: String): Observable<User?> {
        // ?: (Elvis operator, because it looks like elvis ?:) ) is shorthand for (first != null)? first : second
        //TODO: work out how to use elvis here
        return fetchUser(username)
    }

    // if a function/method only has one expression, you can use = instead of wrapping in { }
    override fun fetchUser(username: String): Observable<User?> = gitHub.user(username)


    override fun getStoredUser(username: String): Observable<User?> = Observable.just(null) // return null for the meantime

    override fun storeUser(user: User) {
        TODO("Implement this when you have time")
    }
}