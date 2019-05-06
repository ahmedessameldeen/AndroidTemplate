package com.squad.androidtemplate.ui.login.data

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class WelcomeRepository(val dataSource: WelcomeDataSource)
