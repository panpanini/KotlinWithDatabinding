package nz.co.panpanini.kotlindatabinding.repository

import rx.Observable

/**
 * Created by matthewvern on 2017/01/30.
 */
interface Repository<T> {

    /**
     * Gets T from local cache OR internet if not available
     */
    fun get(key: String): Observable<T?>

    /**
     * Gets T from the internet, or null if not available
     */
    fun fetch(key: String): Observable<T?>

    /**
     * Gets T from the local storage, or null if not available
     */
    fun stored(key: String): Observable<T?>

    /**
     * Stores T in local cache
     */
    fun store(item: T)

}