package com.example.jetpackpro2.datasource

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tv_shows_entity")
@Parcelize
data class DataTvEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvId")
    var tvId: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "rate")
    var rate: Double,
    @ColumnInfo(name = "poster")
    var poster: String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "yearRealese")
    var yearRealese: String,

    @ColumnInfo(name = "banner")
    var banner: String,
    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,
): Parcelable