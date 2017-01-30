package nz.co.panpanini.kotlindatabinding.api

import nz.co.panpanini.kotlindatabinding.model.Repo
import nz.co.panpanini.kotlindatabinding.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by matthewvern on 2017/01/27.
 */
interface GitHub {

    @GET("users/{user}")
    fun user(@Path("user") username: String): Observable<User?>

    @GET("users/{user}/repos")
    fun repo(@Path("user") username: String): Observable<List<Repo>?>
}