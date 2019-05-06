package com.squad.androidtemplate.network

import javax.inject.Inject

/**
 * <h1>ApiHelper</h1>
 *
 * A class contains all the APIs endpoints
 *
 * @constructor
 * @param apiHeader
 * @author Ahmed Salah
 * @since 11/11/18.
 */
class AppApiHelper @Inject constructor(private val apiHeader: ApiHeader) : ApiHelper