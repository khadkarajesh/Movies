package com.rajesh.movies


interface Repository {
    fun onSuccess(a: String)
    fun onFailure(b: String)
}