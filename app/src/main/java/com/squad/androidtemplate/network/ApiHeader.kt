package com.squad.androidtemplate.network

import javax.inject.Inject


/**
 * <h1>ApiHeader</h1>
 * A class contains PublicApiHeader and ProtectedApiHeader Injectors
 *
 * @constructor
 * @param publicApiHeader
 * @param protectedApiHeader
 *
 * @author Ahmed Salah
 * @since 11/11/18.
 */
class ApiHeader @Inject constructor(internal val publicApiHeader: PublicApiHeader, internal val protectedApiHeader: ProtectedApiHeader) {

    class PublicApiHeader @Inject constructor()

    class ProtectedApiHeader @Inject constructor()

}