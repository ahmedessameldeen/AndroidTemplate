/*
 *  Copyright 2017 Rozdoum
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.squad.androidtemplate.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.squad.androidtemplate.R
import com.squad.androidtemplate.utils.LogUtil

/**
 * Created by alexey on 13.04.17.
 */


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        if (remoteMessage!!.data != null && remoteMessage.data[ACTION_TYPE_KEY] != null) {
            handleRemoteMessage(remoteMessage)
        } else {
            LogUtil.logError(
                TAG,
                "onMessageReceived()",
                RuntimeException("FCM remoteMessage doesn't contains Action Type")
            )
        }
    }

    private fun handleRemoteMessage(remoteMessage: RemoteMessage) {
        val receivedActionType = remoteMessage.data[ACTION_TYPE_KEY]
        LogUtil.logDebug(TAG, "Message Notification Action Type: " + receivedActionType!!)


    }

    private fun sendNotification(
        channel: Channel,
        notificationTitle: String?,
        notificationBody: String?,
        bitmap: Bitmap?,
        intent: Intent,
        backIntent: Intent? = null
    ) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val pendingIntent: PendingIntent

        if (backIntent != null) {
            backIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val intents = arrayOf(backIntent, intent)
            pendingIntent = PendingIntent.getActivities(this, notificationId++, intents, PendingIntent.FLAG_ONE_SHOT)
        } else {
            pendingIntent = PendingIntent.getActivity(this, notificationId++, intent, PendingIntent.FLAG_ONE_SHOT)
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this, channel.id)
        notificationBuilder.setAutoCancel(true)   //Automatically delete the notification
            .setSmallIcon(R.drawable.notify_panel_notification_icon_bg) //Notification icon
            .setContentIntent(pendingIntent)
            .setContentTitle(notificationTitle)
            .setContentText(notificationBody)
            .setLargeIcon(bitmap)
            .setSound(defaultSoundUri)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(channel.id, getString(channel.channelName), importance)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = ContextCompat.getColor(this, R.color.colorPrimary)
            notificationChannel.enableVibration(true)
            notificationBuilder.setChannelId(channel.id)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(notificationId++, notificationBuilder.build())
    }

    internal enum class Channel(
        var id: String,
        var channelName: Int
    ) {
        NEW_LIKE("new_like_id", R.string.new_like_channel_name),
        NEW_COMMENT("new_comment_id", R.string.new_comment_channel_name)
    }

    companion object {

        private val TAG = MyFirebaseMessagingService::class.java.simpleName

        private var notificationId = 0

        private val POST_ID_KEY = "postId"
        private val AUTHOR_ID_KEY = "authorId"
        private val ACTION_TYPE_KEY = "actionType"
        private val TITLE_KEY = "title"
        private val BODY_KEY = "body"
        private val ICON_KEY = "icon"
        private val ACTION_TYPE_NEW_LIKE = "new_like"
        private val ACTION_TYPE_NEW_COMMENT = "new_comment"
        private val ACTION_TYPE_NEW_POST = "new_post"
    }
}
