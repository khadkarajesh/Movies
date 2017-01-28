package com.rajesh.movies

open class Person(var name: String, var address: String, var maritalStatus: Boolean = false) : Repository {
    override fun onSuccess(a: String) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailure(b: String) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //creating static method
    companion object {
        @JvmStatic
        val data: String = "youtube.com"
    }
}
