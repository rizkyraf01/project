package com.dicoding.jetpacksubmission3.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST_MOVIE = "newest_movie"
    const val NEWEST_TV = "newest_tv"
    const val RANDOM = "random"
    const val RANDOMIN = "randomin"
    const val MOVIES_ENTITY = "movies_entity"
    const val TV_SHOWS_ENTITY = "tv_shows_entity"

    fun getSortedQuery(filter: String, table: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $table ")
        if (filter == NEWEST_MOVIE) {
            simpleQuery.append("ORDER BY rate DESC")
        } else if (filter == NEWEST_TV) {
            simpleQuery.append("ORDER BY rate DESC")
        }else if (filter == RANDOM){
            simpleQuery.append("ORDER BY yearRealese DESC")
        }
        else if (filter == RANDOMIN){
            simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}