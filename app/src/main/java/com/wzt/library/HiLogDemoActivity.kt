package com.wzt.library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wzt.hilog.HiLog
import com.wzt.hilog.HiLogConfig
import com.wzt.hilog.HiLogManager
import com.wzt.hilog.HiLogType
import com.wzt.hilog.printer.HiViewPrinter
import kotlinx.android.synthetic.main.activity_hi_log_demo.*

class HiLogDemoActivity : AppCompatActivity() {
    var viewPrinter :HiViewPrinter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hi_log_demo)
        viewPrinter = HiViewPrinter(this)
        HiLogManager.getInstance().addPrinter(viewPrinter)
        btn_log.setOnClickListener {
            printLog()
        }
        viewPrinter?.viewProvider?.showFloatingView()
    }

    override fun onDestroy() {
        super.onDestroy()
        HiLogManager.getInstance().removePrinter(viewPrinter)
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
