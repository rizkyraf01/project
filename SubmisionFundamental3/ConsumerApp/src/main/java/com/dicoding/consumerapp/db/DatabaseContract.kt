package com.dicoding.consumerapp.db

import android.provider.BaseColumns
import android.net.Uri

object DatabaseContract {

    const val AUTHORITY = "com.dicoding.submisionfundamental3"
    const val SCHEME = "content"

    class FavoriteColumns : BaseColumns{
        companion object{
            const val TABLE_NAME = "favorite"
            const val USERNAME = "username"
            const val NAME = "name"
            const val USERIMAGE = "userImage"
            const val COMPANY = "company"
            const val LOCATION = "location"
            const val REPOSITORY = "repository"
            const val FOLLOWING = "following"
            const val FOLLOWERS = "followers"
            const val FAVORITE = "userfavorite"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()

        }
    }
}