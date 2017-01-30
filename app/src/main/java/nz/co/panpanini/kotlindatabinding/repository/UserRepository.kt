package nz.co.panpanini.kotlindatabinding.repository

import nz.co.panpanini.kotlindatabinding.api.GitHub
import nz.co.panpanini.kotlindatabinding.model.User
import rx.Observable

/**
 * Created by matthewvern on 2017/01/27.
 */
abstract class UserRepository(val gitHub: GitHub): Repository<User> {

    /**
     * Gets a user from local cache OR internet if not available
     */
    abstract fun getUser(username: String): Observable<User?>

    /**
     * Gets a user from the internet, or null if not available
     */
    abstract fun fetchUser(username: String): Observable<User?>

    /**
     * Gets a user from local cache, or null if not available
     */
    abstract fun getStoredUser(username: String): Observable<User?>

    /**
     * Stores a user in local cache
     */
    abstract fun storeUser(user: User)


    override fun get(key: String): Observable<User?> = getUser(key)

    override fun fetch(key: String): Observable<User?> = fetchUser(key)

    override fun stored(key: String): Observable<User?> = getStoredUser(key)

    override fun store(item: User) = storeUser(item)

}