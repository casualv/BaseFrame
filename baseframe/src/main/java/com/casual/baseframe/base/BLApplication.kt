package com.casual.baseframe.base

import android.app.Application
import com.casual.baseframe.BuildConfig
import com.casual.baseframe.utils.Utils
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.squareup.leakcanary.LeakCanary

abstract class BLApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        SmartRefreshLayout.setDefaultRefreshInitializer { context, layout ->
            layout.apply {
                autoLoadMore()
            }
        }
        if(BuildConfig.DEBUG){
            //泄漏分析自身也会有个进程，这里判断如果是那个泄漏分析进程的话则不需要进行分析
            if(!LeakCanary.isInAnalyzerProcess(this)){
                LeakCanary.install(this);
                LeakCanary.enableDisplayLeakActivity(this);
            }
        }
    }
}