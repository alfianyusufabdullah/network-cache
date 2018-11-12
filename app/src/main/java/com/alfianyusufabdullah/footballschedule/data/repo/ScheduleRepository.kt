package com.alfianyusufabdullah.footballschedule.data.repo

import com.alfianyusufabdullah.footballschedule.data.local.LocalDataSource
import com.alfianyusufabdullah.footballschedule.data.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ScheduleRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource) :
    ScheduleRepositoryImpl {

    override fun getSchedule(scheduleId: String, callback: ScheduleRepositoryCallback) = GlobalScope.async {
        val response = remoteDataSource.getSchedule(scheduleId)

        launch(Dispatchers.Main) {

            response.events?.let {
                callback.onScheduleLoaded(response.events)
                localDataSource.setScheduleCache(scheduleId, response)
            } ?: kotlin.run {
                val scheduleCache = localDataSource.getScheduleCache(scheduleId)

                scheduleCache.events?.let {
                    callback.onScheduleLoadedFromCache(it)
                } ?: kotlin.run {
                    callback.onScheduleFailToLoad()
                }
            }
        }
    }

    override fun clearScheduleCache(callback: ScheduleRepositoryCallback) {
        localDataSource.clearCache()
        callback.onScheduleCacheClear()
    }
}