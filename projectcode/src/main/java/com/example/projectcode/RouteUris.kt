package com.example.projectcode

import android.app.Activity
import androidx.core.net.toUri
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter

public object RouteUris {
    /**
     * 温馨提示,每次注册路由，名称应该 /模块名称/功能/名称
     *
     * 不同模块使用相同的Group，数据会被覆盖，导致界面跳转不成功的情况
     */
    public object Activitys {
        private const val MODULE_MAIN = "module_main"
        const val MODULE_MAIN_MAIN = "/$MODULE_MAIN/main"

        private const val MODULE_MAP = "module_map"
        const val MODULE_MAP_MAIN = "/$MODULE_MAP/main"
    }


    public object Fragments {
    }

    public  object Services {
    }

}

fun String.doRoute(context: Activity, reqCode: Int = -1, init: (Postcard.() -> Unit)?=null) {
    val postcard = ARouter.getInstance().build(this.toUri())
    init?.let { postcard.it() }
    postcard.navigation(context, reqCode)
}