package com.alfianyusufabdullah.footballschedule.data.remote

import com.alfianyusufabdullah.footballschedule.data.entity.ScheduleResponse

interface RemoteDataSourceImpl {
    fun getSchedule(scheduleId: String) : ScheduleResponse
}