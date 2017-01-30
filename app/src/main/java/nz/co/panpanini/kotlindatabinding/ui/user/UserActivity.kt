package nz.co.panpanini.kotlindatabinding.ui.user

import android.content.Intent
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import nz.co.panpanini.kotlindatabinding.R
import nz.co.panpanini.kotlindatabinding.api.GitHub
import nz.co.panpanini.kotlindatabinding.databinding.ActivityMainBinding
import nz.co.panpanini.kotlindatabinding.model.User
import nz.co.panpanini.kotlindatabinding.repository.SharedPreferencesUserRepository
import nz.co.panpanini.kotlindatabinding.ui.repo.RepoActivity
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers


class UserActivity : AppCompatActivity(), UserView {

    val binding: ActivityMainBinding by lazy {
        // if we do this lazily, we can make sure its not called until the activity is created
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

    }

    // don't have to specify a type, it will be inferred, however it can be better at times to specify for readability
    val presenter by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
        val retroUser = retrofit.create(GitHub::class.java) // this is how you say GitHub.class

        // similar to Ruby - if we create this object on the last line of the lambda, it will return it
        // we don't have to specify return
        UserPresenter(this, SharedPreferencesUserRepository(retroUser))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.presenter = presenter
    }

    override fun bindUser(user: User) {
        // databinding you are amazing - this is all we have to do!
        binding.user = user
    }

    override fun networkError(message: String) {
        Toast.makeText(this, "A network error occurred: $message", Toast.LENGTH_LONG).show()
        binding.user = null
    }

    override fun openRepoView(username: String) {
        val intent = RepoActivity.createIntent(this, username)
        startActivity(intent)
    }


    companion object {
        // JvmStatic annotation tells the Java Interop that this is a static method.
        // Kotlin static methods are defined by wrapping them in a companion object
        @JvmStatic
        // This Binding adapter tells the Data Binder how to handle custom binds - in this case
        // using Picasso to display an image
        @BindingAdapter("bind:imageUrl")
        fun loadImage(view: ImageView, imageUrl: String?) {

            imageUrl?.let {
                Picasso.with(view.context)
                        .load(imageUrl)
                        .into(view)
            }
        }
    }


}
