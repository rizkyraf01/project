package com.dicoding.jetpacksubmission3.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingRecource {

    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        idlingResource.increment()
    }
    fun decrement() {
        idlingResource.decrement()
    }
}