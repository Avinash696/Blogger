package com.example.blogger.blogModels

import android.net.Uri
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class googleSignModel(
    @SerializedName("personGivenName")
    val personGivenName: String?,
    @SerializedName("personPhoto")
    val personPhoto: Uri?
) : Serializable