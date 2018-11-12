package com.alfianyusufabdullah.footballschedule.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alfianyusufabdullah.footballschedule.R
import com.alfianyusufabdullah.footballschedule.data.entity.ScheduleItem

class ScheduleAdapter(private val data: MutableList<ScheduleItem>) :
    RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    fun submitList(newData: MutableList<ScheduleItem>) {
        this.data.clear()
        this.data.addAll(newData)

        this.notifyDataSetChanged()
    }

    fun clear() {
        this.data.clear()

        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.schedule_list_item, parent, false)
        return ScheduleViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val itemMatchDate = itemView.findViewById<TextView>(R.id.itemMatchDate)
        private val itemScoreHome = itemView.findViewById<TextView>(R.id.itemScoreHome)
        private val itemScoreAway = itemView.findViewById<TextView>(R.id.itemScoreAway)
        private val itemTeamHome = itemView.findViewById<TextView>(R.id.itemTeamHome)
        private val itemTeamAway = itemView.findViewById<TextView>(R.id.itemTeamAway)

        fun bind(scheduleItem: ScheduleItem) {
            itemMatchDate.text = scheduleItem.strDate
            itemScoreHome.text = scheduleItem.intHomeScore
            itemScoreAway.text = scheduleItem.intAwayScore
            itemTeamHome.text = scheduleItem.strHomeTeam
            itemTeamAway.text = scheduleItem.strAwayTeam
        }
    }
}