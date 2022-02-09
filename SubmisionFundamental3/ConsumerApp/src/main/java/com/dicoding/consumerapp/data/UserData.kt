package com.dicoding.consumerapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserData(
        var username: String = "",
        var name: String = "",
        var userImage: String = "",
        var company: String = "",
        var location: String = "",
        var following: String = "",
        var followers: String = "",
        var repository: String = "",
        var userfavorite: String = ""
): Parcelable
