package nz.co.panpanini.kotlindatabinding.ui.user

import nz.co.panpanini.kotlindatabinding.model.User

/**
 * Created by matthewvern on 2017/01/27.
 */
interface UserView {

    fun bindUser(user: User)

    fun networkError(message: String)

    fun openRepoView(username: String)
}