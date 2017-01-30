package nz.co.panpanini.kotlindatabinding.model

import com.google.gson.annotations.SerializedName

/**
 * Created by matthewvern on 2017/01/30.
 */
data class Repo(
        @SerializedName("full_name")
        val name: String,
        val description: String,
        val language: String,
        val fork: Boolean
) {
}