package com.wzt.library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wzt.hilog.HiLog
import com.wzt.hilog.HiLogConfig
import com.wzt.hilog.HiLogType
import kotlinx.android.synthetic.main.activity_hi_log_demo.*

class HiLogDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hi_log_demo)
        btn_log.setOnClickListener {
            printLog()
        }
    }

    private fun printLog() {
        HiLog.log(object : HiLogConfig() {
            override fun includeThread(): Boolean {
                return true
            }

            override fun stackTraceDepth(): Int {
                return 0
            }
        }, HiLogType.E, "-----", 5566)
        HiLog.a("9900")
    }
}
