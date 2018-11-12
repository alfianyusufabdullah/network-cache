package com.alfianyusufabdullah.footballschedule.data.local

import com.alfianyusufabdullah.footballschedule.data.entity.ScheduleResponse

interface LocalDataSourceImpl {
    fun setScheduleCache(scheduleId: String, scheduleResponse: ScheduleResponse)
    fun getScheduleCache(scheduleId: String): ScheduleResponse
    fun clearCache()
}