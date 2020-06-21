package com.wzt.library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wzt.hilog.HiLog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        HiLog.v(null, "wzt")
    }
}
