package com.dicoding.jetpacksubmission3.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movies_entity")
@Parcelize
data class MoviesEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "yearRealese")
    var yearRealese: String,

    @ColumnInfo(name = "genre")
    var genre: String,

    @ColumnInfo(name = "duration")
    var duration: String,

    @ColumnInfo(name = "rate")
    var rate: Double,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "poster")
    var poster: String,

    @ColumnInfo(name = "banner")
    var banner: String,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,
): Parcelable
