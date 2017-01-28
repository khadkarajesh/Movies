package com.rajesh.movies


class Movie : Repository{
    override fun onFailure(b: String) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess(a: String) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var name: String = ""
    var rating: Int = 0
    var cost: Double = 0.0
}

