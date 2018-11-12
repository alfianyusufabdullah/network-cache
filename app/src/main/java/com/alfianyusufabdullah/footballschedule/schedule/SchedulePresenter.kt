package com.alfianyusufabdullah.footballschedule.schedule

import com.alfianyusufabdullah.footballschedule.data.entity.ScheduleItem
import com.alfianyusufabdullah.footballschedule.data.repo.ScheduleRepository
import com.alfianyusufabdullah.footballschedule.data.repo.ScheduleRepositoryCallback
import kotlinx.coroutines.Job

class SchedulePresenter(private val scheduleRepository: ScheduleRepository) : ScheduleContract.Presenter {

    private var view: ScheduleContract.View? = null
    private var job: Job? = null

    fun attach(view: ScheduleContract.View) {
        this.view = view
    }

    fun detach() {
        this.view = null
        job?.let {
            if (it.isActive) {
                it.cancel()
            }
        }
    }

    override fun getSchedule(scheduleId: String) {
        job = scheduleRepository.getSchedule(scheduleId, object : ScheduleRepositoryCallback() {
            override fun onScheduleLoaded(data: MutableList<ScheduleItem>?) {
                view?.onScheduleLoaded(data)
            }

            override fun onScheduleLoadedFromCache(data: MutableList<ScheduleItem>?) {
                view?.onScheduleLoadedFromCache(data)
            }

            override fun onScheduleFailToLoad() {
                view?.onScheduleFailToLoad()
            }
        })
    }

    override fun clearSchedule() {
        scheduleRepository.clearScheduleCache(object : ScheduleRepositoryCallback() {
            override fun onScheduleCacheClear() {
                view?.onScheduleCacheClear()
            }
        })
    }
}