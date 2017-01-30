package nz.co.panpanini.kotlindatabinding.ui.repo

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import nz.co.panpanini.kotlindatabinding.R
import nz.co.panpanini.kotlindatabinding.api.GitHub
import nz.co.panpanini.kotlindatabinding.model.Repo
import nz.co.panpanini.kotlindatabinding.repository.RepoRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers

class RepoActivity : AppCompatActivity(), RepoView {

    val recyclerView by lazy {
        findViewById(R.id.activity_repo) as RecyclerView
    }

    val adapter = RepoAdapter()

    val presenter by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
        val retroUser = retrofit.create(GitHub::class.java) // this is how you say GitHub.class

        // similar to Ruby - if we create this object on the last line of the lambda, it will return it
        // we don't have to specify return
        RepoPresenter(this, RepoRepository(retroUser))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        presenter.fetchRepos(intent.getStringExtra(USERNAME))
    }

    override fun addRepo(repo: Repo) = adapter.add(repo)

    override fun networkError(message: String) = Toast.makeText(this, "Network Error: $message", Toast.LENGTH_LONG).show()


    companion object {
        private val USERNAME = "USERNAME"

        fun createIntent(context: Context, username: String): Intent {
            val intent = Intent(context, RepoActivity::class.java)
            intent.putExtra(USERNAME, username)

            return intent
        }
    }
}
