package nz.co.panpanini.kotlindatabinding.ui.repo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import nz.co.panpanini.kotlindatabinding.databinding.ViewRepoBinding
import nz.co.panpanini.kotlindatabinding.model.Repo
import java.util.*

/**
 * Created by matthewvern on 2017/01/30.
 */
class RepoAdapter : RecyclerView.Adapter<RepoViewHolder>(){

    val items = ArrayList<Repo>()

    override fun onBindViewHolder(holder: RepoViewHolder?, position: Int) {
        val repo = items[position]
        holder?.bind(repo)
    }

    override fun getItemCount(): Int = items.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ViewRepoBinding.inflate(inflater, parent, false)

        // we do have to use return in a function (I dunno why ¯\_(ツ)_/¯ )
        return RepoViewHolder(binding)
    }


    fun add(repo: Repo) {
        items.add(repo)
        notifyItemInserted(items.size)
    }

    fun remove(repo: Repo) {
        val position = items.indexOf(repo)
        items.removeAt(position)
        notifyItemRemoved(position)
    }
}