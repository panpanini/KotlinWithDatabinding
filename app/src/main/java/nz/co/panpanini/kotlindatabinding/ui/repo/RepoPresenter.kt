package nz.co.panpanini.kotlindatabinding.ui.repo

import nz.co.panpanini.kotlindatabinding.repository.RepoRepository
import rx.Observable
import rx.android.schedulers.AndroidSchedulers

/**
 * Created by matthewvern on 2017/01/30.
 */
class RepoPresenter(val repoView: RepoView, val repoRepository: RepoRepository) {

    fun fetchRepos(username: String) {
        repoRepository.get(username)
                // turn a list of repos into individual observables
                .flatMap { Observable.from(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ repo ->
                    repo?.let {
                        repoView.addRepo(repo)
                        return@subscribe
                    }

                    repoView.networkError("unable to add Repo")
                },
                { error ->
                    val message = error.message?: "network"
                    repoView.networkError(message)
                })
    }

}