package com.example.ablyproject.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import org.reactivestreams.Publisher

fun <T> Publisher<T>.toLiveData()  = LiveDataReactiveStreams.fromPublisher(this) as LiveData<T>