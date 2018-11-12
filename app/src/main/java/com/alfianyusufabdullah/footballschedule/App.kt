package com.alfianyusufabdullah.footballschedule

import android.app.Application
import com.alfianyusufabdullah.footballschedule.modul.adapterModule
import com.alfianyusufabdullah.footballschedule.modul.dataModule
import com.alfianyusufabdullah.footballschedule.modul.presenterModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(dataModule, presenterModule, adapterModule))
    }
}