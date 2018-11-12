package com.alfianyusufabdullah.footballschedule.data.local

import android.content.Context
import androidx.core.content.edit
import com.alfianyusufabdullah.footballschedule.data.entity.ScheduleResponse
import com.google.gson.Gson
import java.lang.Exception


class LocalDataSource(context: Context, private val gson: Gson) : LocalDataSourceImpl {

    private val preference = context.getSharedPreferences("cache", Context.MODE_PRIVATE)

    override fun setScheduleCache(scheduleId: String, scheduleResponse: ScheduleResponse) {
        preference.edit {
            putString(scheduleId, gson.toJson(scheduleResponse))
        }
    }

    override fun getScheduleCache(scheduleId: String): ScheduleResponse {
        return try {
            gson.fromJson(preference.getString(scheduleId, ""), ScheduleResponse::class.java)
        } catch (e: Exception) {
            ScheduleResponse(null)
        }
    }

    override fun clearCache() {
        preference.edit { clear() }
    }
}