package  com.crushcoder.kmovies.rest.retrofit

interface Result<T> {
    fun success(data: T)
}


