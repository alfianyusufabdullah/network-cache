package com.alfianyusufabdullah.footballschedule.schedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfianyusufabdullah.footballschedule.R
import com.alfianyusufabdullah.footballschedule.data.entity.ScheduleItem
import kotlinx.android.synthetic.main.schedule_activity.*
import org.koin.android.ext.android.inject

class ScheduleActivity : AppCompatActivity(), ScheduleContract.View {

    override fun onScheduleCacheClear() {
        Toast.makeText(this, "Cache Clear", Toast.LENGTH_LONG).show()
    }

    override fun onScheduleLoaded(data: MutableList<ScheduleItem>?) {
        data?.let { scheduleAdapter.submitList(it) }

        tvError.visibility = View.GONE
    }

    override fun onScheduleLoadedFromCache(data: MutableList<ScheduleItem>?) {
        data?.let { scheduleAdapter.submitList(it) }

        tvError.visibility = View.GONE
        Toast.makeText(this, "Network error, Schedule Load From Cache", Toast.LENGTH_LONG).show()
    }

    override fun onScheduleFailToLoad() {
        scheduleAdapter.clear()

        tvError.visibility = View.VISIBLE
    }

    private val schedulePresenter by inject<SchedulePresenter>()
    private val scheduleAdapter by inject<ScheduleAdapter>()
    private var league = emptyArray<String>()
    private var leagueId = listOf("4328", "4329", "4331", "4332", "4334", "4335")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)

        initResources()
        initSpinnerItem()
        initScheduleItem()
    }

    private fun initScheduleItem() {
        rvSchedule.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            adapter = scheduleAdapter
        }
    }

    private fun initResources() {
        league = resources.getStringArray(R.array.league)
    }

    private fun initSpinnerItem() {
        val spScheduleAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, league)
        spSchedule.adapter = spScheduleAdapter
        spSchedule.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                schedulePresenter.getSchedule(leagueId[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                /// TODO : Jodoh ditangan tuhan, gak sabar ? silahkeun menghadap tuhan
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0, 1, 1, "Clear Cache")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == 1) { schedulePresenter.clearSchedule() }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        schedulePresenter.attach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        schedulePresenter.detach()
    }
}
