package nz.co.panpanini.kotlindatabinding.model

import com.google.gson.annotations.SerializedName

/**
 * Created by matthewvern on 2017/01/27.
 */
data class User(
        @SerializedName("login")
        val username: String,
        val name: String,
        @SerializedName("avatar_url")
        val imageUrl: String,
        val location: String,
        @SerializedName("public_repos")
        val numberOfRepos: Int
)
{

    // this is like static methods
    companion object {
        /*
        Okay, a couple of things here.
        inline - this keyword takes the code here and inserts it in the place where it is called.
                 that means we don't have an extra method, because its magicked away by Kotlin.
         Builder.() -> Unit - says that we expect a function/lambda to be passed in as an argument
         .apply(block) - apply takes any code in the block and runs it in the context of the object
                         apply() is being called on (in this case, the Builder). This allows you to
                         call methods and access fields on the Builder class as if you were the builder.
                         This means you can just say username = "name", instead of having to type
                         builder.username = "name"
         */
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder() {
        var username: String? = null
        var name: String? = null
        var imageUrl: String? = null
        var location: String? = null
        var numberOfRepos: Int = 0

        fun build() = User(
                    username?: "",
                    name?: "",
                    imageUrl?: "",
                    location?: "",
                    numberOfRepos
            )

    }

}