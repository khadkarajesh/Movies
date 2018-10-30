package com.crushcoder.kmovies.rest

sealed class State
object LOADING : State()
object SUCCESS : State()
class FAILURE(var message: String) : State()