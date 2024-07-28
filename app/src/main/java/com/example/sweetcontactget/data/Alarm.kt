package com.example.sweetcontactget.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.Calendar

class Alarm : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            val title = intent.getStringExtra("title") ?: "알림"
            val message = intent.getStringExtra("message") ?: "알림입니다."
            MyNotification(context!!).deliverNotification(title, message)
        }
    }

    fun addAlarm(
        context: Context,
        selectedYear: Int,
        selectedMonth: Int,
        selectedDay: Int,
        selectedHour: Int,
        selectedMinute: Int,
        title: String,
        content: String
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, Alarm::class.java).apply {
            putExtra("title", title)
            putExtra("message", content)
        }
        val pIntent = PendingIntent.getBroadcast(
            context, 0, intent,
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_MUTABLE
        )

        val cal = Calendar.getInstance()
        cal.set(selectedYear, selectedMonth - 1, selectedDay, selectedHour, selectedMinute, 0)

        val triggerTime = cal.timeInMillis

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pIntent)
    }
}
