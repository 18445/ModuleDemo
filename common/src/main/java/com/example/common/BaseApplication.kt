package com.example.common

import android.app.Application
import android.util.Log
import com.example.common.config.BaseConfig

/**
 *
 * @ProjectName:    ModuleDemo
 * @Package:        com.example.common.config
 * @ClassName:      BaseApplication
 * @Author:         Yan
 * @CreateDate:     2022年05月23日 22:14:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:     //TODO
 */
class BaseApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Log.i(BaseConfig.TAG,"common/BaseApplication onCreate: ")
    }
}