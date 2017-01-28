package com.rajesh.movies

/**
 * Unlike java interface are public by default
 * Classes as well are default
 *
 */
interface Repository {
    fun onSuccess(a: String)
    fun onFailure(b: String)
}