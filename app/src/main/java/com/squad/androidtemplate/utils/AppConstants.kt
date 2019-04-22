package com.squad.androidtemplate.utils


object AppConstants {

    internal val APP_DB_NAME = "squad.db"
    internal val PREF_NAME = "squad_pref"
    internal val EMPTY_EMAIL_ERROR = 1001
    internal val INVALID_EMAIL_ERROR = 1002
    internal val EMPTY_PASSWORD_ERROR = 1003
    internal val LOGIN_FAILURE = 1004
    internal val NULL_INDEX = -1L
    const val TYPE_EMPTY = "empty"
    const val TYPE_LOADING = "loading"
    const val TYPE_TEXT = "text/html"
    const val TYPE_YOUTUBE = "youtube"
    const val TYPE_IMAGE = "image"
    const val TYPE_GALLERY = "gallery"
    // viewHolder types


    enum class LoggedInMode constructor(val type: Int) {
        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3)
    }
}