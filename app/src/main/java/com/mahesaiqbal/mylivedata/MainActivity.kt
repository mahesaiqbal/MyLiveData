package com.mahesaiqbal.mylivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mLiveDataTimerViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mLiveDataTimerViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        subscribe()
    }

    private fun subscribe() {
        val elapsedTimeObserver: Observer<Long> = object : Observer<Long> {
            override fun onChanged(long: Long?) {
                val newText = resources.getString(R.string.seconds, long)
                timer_textview.text = newText
            }
        }

        mLiveDataTimerViewModel?.getElapsedTime()?.observe(this, elapsedTimeObserver)
    }
}
