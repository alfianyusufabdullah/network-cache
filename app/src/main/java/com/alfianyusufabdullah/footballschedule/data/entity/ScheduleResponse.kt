package com.alfianyusufabdullah.footballschedule.data.entity

import com.google.gson.annotations.SerializedName

data class ScheduleResponse(

	@field:SerializedName("events")
	val events: MutableList<ScheduleItem>? = null
)