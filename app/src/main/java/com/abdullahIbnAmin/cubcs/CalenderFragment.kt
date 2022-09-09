package com.abdullahIbnAmin.cubcs

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView

class CalenderFragment : Fragment() {

    lateinit var calendar: CalendarView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calender, container, false)

        calendar = view.findViewById(R.id.calendarView)

        calendar.setOnDateChangeListener(
            object: CalendarView.OnDateChangeListener{
                override fun onSelectedDayChange(p0: CalendarView, p1: Int, p2: Int, p3: Int) {
                    val year: Int = p1
                    val month: Int = p2 + 1
                    val day: Int = p3
                    Log.d("test", "$day/$month/$year")
                }

            }
        )

//        val year = 2022
//        val month = 7
//        val day = 19
//        val c = Calendar.getInstance()
//
//
//        c.set(Calendar.YEAR, year)
//        c.set(Calendar.MONTH, month)
//        c.set(Calendar.DAY_OF_MONTH, day)
//
//        val milli = c.timeInMillis
//
//        calendar.date = milli
//
//
        return view
    }

}