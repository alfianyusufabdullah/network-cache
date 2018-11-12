package com.alfianyusufabdullah.footballschedule.data.entity

import com.google.gson.annotations.SerializedName

data class ScheduleItem(

	@field:SerializedName("strHomeTeam")
	val strHomeTeam: String? = null,

	@field:SerializedName("strTime")
	val strTime: String? = null,

	@field:SerializedName("strAwayTeam")
	val strAwayTeam: String? = null,

	@field:SerializedName("intAwayScore")
	val intAwayScore: String? = null,

	@field:SerializedName("strDate")
	val strDate: String? = null,

	@field:SerializedName("intHomeScore")
	val intHomeScore: String? = null
)