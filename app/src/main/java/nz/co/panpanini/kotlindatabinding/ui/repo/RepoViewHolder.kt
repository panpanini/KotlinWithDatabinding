package nz.co.panpanini.kotlindatabinding.ui.repo

import android.support.v7.widget.RecyclerView
import android.view.View
import nz.co.panpanini.kotlindatabinding.databinding.ViewRepoBinding
import nz.co.panpanini.kotlindatabinding.model.Repo

/**
 * Created by matthewvern on 2017/01/30.
 */
class RepoViewHolder(val binding: ViewRepoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repo: Repo) {
        binding.repo = repo
        binding.executePendingBindings()
    }

}