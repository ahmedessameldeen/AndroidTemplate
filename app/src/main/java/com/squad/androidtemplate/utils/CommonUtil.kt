package com.squad.androidtemplate.utils

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.format.DateUtils
import androidx.core.graphics.drawable.toDrawable
import com.squad.androidtemplate.R
import java.text.SimpleDateFormat
import java.util.*


object CommonUtil {
    object CommonConst {
    }


    /**
     * <h1>showLoadingDialog</h1>
     *
     * A function used to show loading dialog
     *
     * @param context
     */
    fun showLoadingDialog(context: Context?): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.let {
            it.show()
            it.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            it.setContentView(R.layout.progress_dialog)
            it.isIndeterminate = true
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            return it
        }
    }

    fun formatDate(milliseconds: Long, locale: Locale): String {

        val sdf = SimpleDateFormat("dd MMM yyyy", locale)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds * 1000
        val tz = TimeZone.getDefault()
        sdf.timeZone = tz
        return sdf.format(calendar.time)
    }

    fun formatDateY(milliseconds: Long, locale: Locale): String {

        val sdf = SimpleDateFormat("yyyy", locale)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds * 1000
        val tz = TimeZone.getDefault()
        sdf.timeZone = tz
        return sdf.format(calendar.time)
    }

    fun formatDateDMFormat(milliseconds: Long, locale: Locale): String {

        val sdf = SimpleDateFormat("dd MMM", locale)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds * 1000
        val tz = TimeZone.getDefault()
        sdf.timeZone = tz
        return sdf.format(calendar.time)
    }

    fun getTimeAgo(date: Long, context: Context): String {

        if (DateUtils.isToday(date)) {
            if (DateUtils.getRelativeTimeSpanString(
                    date,
                    System.currentTimeMillis(),
                    DateUtils.MINUTE_IN_MILLIS
                ) == context.getResources().getString(R.string._0min)
            ) {
                return context.getResources().getString(R.string.justNow)
            } else {
                return DateUtils.getRelativeTimeSpanString(
                    java.lang.Long.valueOf(date),
                    System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS
                ).toString()
            }
        } else if (isYesterday(date)) {
            return context.getResources().getString(R.string.yesterday)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return formatDate(date, context.resources.configuration.locales[0])
            } else {
                return formatDate(date, context.resources.configuration.locale)
            }
        }
    }

    private fun isYesterday(date: Long): Boolean {
        val now = Calendar.getInstance()
        val cdate = Calendar.getInstance()
        cdate.timeInMillis = date
        now.add(Calendar.DATE, -1)
        return (now.get(Calendar.YEAR) == cdate.get(Calendar.YEAR)
                && now.get(Calendar.MONTH) == cdate.get(Calendar.MONTH)
                && now.get(Calendar.DATE) == cdate.get(Calendar.DATE))
    }
}