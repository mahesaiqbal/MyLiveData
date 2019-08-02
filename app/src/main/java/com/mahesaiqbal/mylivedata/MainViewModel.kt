package com.mahesaiqbal.mylivedata

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import androidx.lifecycle.LiveData

class MainViewModel : ViewModel() {

    private var mElapsedTime: MutableLiveData<Long> = MutableLiveData()
    private val mInitialTime: Long

    init {
        mInitialTime = SystemClock.elapsedRealtime()
        val timer = Timer()

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(newValue)
            }
        }, 1000, 1000)
    }

    fun getElapsedTime(): LiveData<Long> {
        return mElapsedTime
    }
}