package com.alfianyusufabdullah.footballschedule.data.repo

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job

interface ScheduleRepositoryImpl {
    fun getSchedule(scheduleId: String, callback: ScheduleRepositoryCallback) : Deferred<Job>
    fun clearScheduleCache(callback: ScheduleRepositoryCallback)
}