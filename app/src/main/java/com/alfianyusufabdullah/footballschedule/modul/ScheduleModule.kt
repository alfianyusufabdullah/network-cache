package com.alfianyusufabdullah.footballschedule.modul

import com.alfianyusufabdullah.footballschedule.data.local.LocalDataSource
import com.alfianyusufabdullah.footballschedule.data.remote.RemoteDataSource
import com.alfianyusufabdullah.footballschedule.data.repo.ScheduleRepository
import com.alfianyusufabdullah.footballschedule.schedule.ScheduleAdapter
import com.alfianyusufabdullah.footballschedule.schedule.SchedulePresenter
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val dataModule = module {
    single { Gson() }
    single { LocalDataSource(androidContext(), get()) }
    single { RemoteDataSource(get()) }
    single { ScheduleRepository(get(), get()) }
}

val presenterModule = module {
    factory { SchedulePresenter(get()) }
}

val adapterModule = module {
    factory { ScheduleAdapter(mutableListOf()) }
}