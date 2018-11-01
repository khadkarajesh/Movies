package com.crushcoder.kmovies.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crushcoder.kmovies.rest.FAILURE
import com.crushcoder.kmovies.rest.LOADING
import com.crushcoder.kmovies.rest.SUCCESS
import com.crushcoder.kmovies.rest.State
import com.crushcoder.kmovies.rest.retrofit.Result
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import retrofit2.HttpException


abstract class BaseViewModel : ViewModel(), KoinComponent {
    var state: MutableLiveData<State> = MutableLiveData()
    private lateinit var job: Job

    fun onCreate() {
        onActivityCreated()
    }

    abstract fun onActivityCreated()

    fun <T> execute(app: Deferred<T>, success: Result<T>) {
        GlobalScope.launch {
            try {
                state.value = LOADING
                success.success(app.await())
                state.value = SUCCESS
            } catch (e: HttpException) {
                state.value = FAILURE(e.message())
            } catch (e: Exception) {
                state.value = FAILURE(e.message!!)
            }
        }
    }


    fun <T> execute(app: Deferred<T>, response: (T?) -> Unit) {
        GlobalScope.launch {
            try {
                state.value = LOADING
                response(app.await())
                state.value = SUCCESS
            } catch (e: HttpException) {
                state.value = FAILURE(e.message())
            } catch (e: Exception) {
                state.value = FAILURE(e.message!!)
            }
        }
    }
}