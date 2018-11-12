package com.alfianyusufabdullah.footballschedule.data.remote

import com.alfianyusufabdullah.footballschedule.data.entity.ScheduleResponse
import com.google.gson.Gson
import java.net.URL

class RemoteDataSource(private val gson: Gson) : RemoteDataSourceImpl {
    override fun getSchedule(scheduleId: String): ScheduleResponse {
        return try {
            val response =
                URL("https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=$scheduleId").readText()
            gson.fromJson(response, ScheduleResponse::class.java)
        } catch (e: Exception) {
            ScheduleResponse(null)
        }
    }
}