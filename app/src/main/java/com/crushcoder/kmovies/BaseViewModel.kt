package com.crushcoder.kmovies

import androidx.lifecycle.*
import com.crushcoder.kmovies.rest.FAILURE
import com.crushcoder.kmovies.rest.LOADING
import com.crushcoder.kmovies.rest.SUCCESS
import com.crushcoder.kmovies.rest.State
import com.crushcoder.kmovies.rest.retrofit.Result
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.koin.standalone.KoinComponent
import retrofit2.HttpException


abstract class BaseViewModel : ViewModel(), KoinComponent {
    var state: MutableLiveData<State> = MutableLiveData()
    private lateinit var job: Job

    fun onCreate() {
        onActivityCreated()
    }

    abstract fun onActivityCreated()

    fun init(lifecycle: Lifecycle) {
        job = AndroidJob(lifecycle)
    }

    fun <T> execute(app: Deferred<T>, success: Result<T>) {
        launch(UI) {
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

    fun execute(app: Deferred<Unit>) {
        launch(UI) {
            try {
                state.value = LOADING
                app.await()
                state.value = SUCCESS
            } catch (e: HttpException) {
                state.value = FAILURE(e.message())
            } catch (e: Exception) {
                state.value = FAILURE(e.message!!)
            }
        }
    }

    class AndroidJob(lifecycle: Lifecycle) : Job by Job(), LifecycleObserver {
        init {
            lifecycle.addObserver(this)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun destroy() = cancel()
    }
}