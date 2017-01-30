package nz.co.panpanini.kotlindatabinding.ui.repo

import nz.co.panpanini.kotlindatabinding.model.Repo

/**
 * Created by matthewvern on 2017/01/30.
 */
interface RepoView {

    fun addRepo(repo: Repo)

    fun networkError(message: String)
}