package com.alfianyusufabdullah.footballschedule.schedule

import com.alfianyusufabdullah.footballschedule.data.entity.ScheduleItem

interface ScheduleContract {

    interface Presenter{
        fun getSchedule(scheduleId: String)
        fun clearSchedule()
    }

    interface View {
        fun onScheduleLoaded(data: MutableList<ScheduleItem>?)
        fun onScheduleLoadedFromCache(data: MutableList<ScheduleItem>?)
        fun onScheduleFailToLoad()
        fun onScheduleCacheClear()
    }
}