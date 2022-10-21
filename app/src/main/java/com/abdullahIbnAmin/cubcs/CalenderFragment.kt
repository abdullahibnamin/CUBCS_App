package com.abdullahIbnAmin.cubcs

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.abdullahIbnAmin.cubcs.models.Event
import com.abdullahIbnAmin.cubcs.viewModel.MainActivityViewModel
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import kotlinx.android.synthetic.main.fragment_calender.*
import java.text.SimpleDateFormat
import java.util.*
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdullahIbnAmin.cubcs.models.EventItem
import com.abdullahIbnAmin.cubcs.recyclerAdapters.RecyclerAdapterForEvents
import kotlin.collections.ArrayList

class CalenderFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    val dateFormatForMonth = SimpleDateFormat("MMM - yyyy", Locale.getDefault())
    lateinit var eventList: Event

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compactcalendar_month.text = dateFormatForMonth.format(Date())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calender, container, false)
        val compactCalendarView: CompactCalendarView = view.findViewById(R.id.compactcalendar_view)
        compactCalendarView.setUseThreeLetterAbbreviation(true)

        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getObserverForEvent().observe(this, androidx.lifecycle.Observer<Event> {
            if (it != null) {
                Log.d("testt", "${it}")
                try {
                    eventList = it
                    for ((eventIndex, eventData) in eventList.withIndex()) {
                        val event = com.github.sundeepk.compactcalendarview.domain.Event(
                            Color.GREEN,
                            eventData.event_epoch_start_date.toLong(),
                            eventIndex
                        )
                        compactCalendarView.addEvent(event)
                    }
                } catch (e: Exception) {
                    Log.d("testt", "Empty List")
                }

            } else {
                Toast.makeText(requireContext(), "Not Found", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.apiCallForEvents()

        val singleEvent = ArrayList<EventItem>()

        compactCalendarView.setListener(object : CompactCalendarView.CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date?) {
                singleEvent.clear()

                val events = compactCalendarView.getEvents(dateClicked)
                try {
                    for (i in events.toList()) {
//                        Log.d("testt", "Data: ${singleEvent.add(eventList[i.data as Int])}")
                        singleEvent.add(eventList[i.data as Int])
                    }

                    recyclerView = eventRecyclerView
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = RecyclerAdapterForEvents(singleEvent)

                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "Please Wait ...", Toast.LENGTH_LONG).show()
                }
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date) {
                compactcalendar_month.text = dateFormatForMonth.format(firstDayOfNewMonth)
            }

        })
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("testt", "${eventList}")
        eventList.clear()
    }

}


