package com.crushcoder.kmovies.rest.retrofit


@kotlin.annotation.Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Page(
        val value: String = "")