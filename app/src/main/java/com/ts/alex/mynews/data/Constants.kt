package com.ts.alex.mynews.data

const val BASE_URL = "https://newsapi.org/"

@JvmField val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
        "com.ts.alex.mynews.data.workmanager_Channel_name"
const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
        "Shows notifications whenever work starts"
@JvmField val NOTIFICATION_TITLE: CharSequence = "News update start"
const val CHANNEL_ID = "VERBOSE_NOTIFICATION"
const val NOTIFICATION_ID = 1

const val WORK_NAME = "news_update_work"

