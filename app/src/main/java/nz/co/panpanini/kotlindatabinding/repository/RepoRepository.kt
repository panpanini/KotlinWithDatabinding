package nz.co.panpanini.kotlindatabinding.repository

import nz.co.panpanini.kotlindatabinding.api.GitHub
import nz.co.panpanini.kotlindatabinding.model.Repo
import rx.Observable

/**
 * Created by matthewvern on 2017/01/30.
 */
class RepoRepository(val gitHub: GitHub) {


    fun get(key: String): Observable<List<Repo>?> {
        return fetch(key)
    }

    fun fetch(key: String): Observable<List<Repo>?> = gitHub.repo(key)

    fun stored(key: String): Observable<Repo?> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun store(item: Repo) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}