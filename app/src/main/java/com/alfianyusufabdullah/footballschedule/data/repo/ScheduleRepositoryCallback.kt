package com.alfianyusufabdullah.footballschedule.data.repo

import com.alfianyusufabdullah.footballschedule.data.entity.ScheduleItem

abstract class ScheduleRepositoryCallback {
    open fun onScheduleLoaded(data: MutableList<ScheduleItem>?){}
    open fun onScheduleLoadedFromCache(data: MutableList<ScheduleItem>?){}
    open fun onScheduleFailToLoad(){}
    open fun onScheduleCacheClear(){}
}