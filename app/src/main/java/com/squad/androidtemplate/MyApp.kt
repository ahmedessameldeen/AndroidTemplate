package com.squad.androidtemplate

import android.content.Context
import android.graphics.Typeface
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.squad.androidtemplate.di.appModule
import org.koin.android.ext.android.startKoin

class MyApp: MultiDexApplication() {

    private val geDinarOneMediumLocationPath: String = "fonts/ge_dinar_one_medium.otf"
    private val latoBoldLocationPath: String = "fonts/lato_bold.ttf"
    private val lotoBoldItalicLocationPath: String = "fonts/lato_bold_italic.ttf"
    private val latoItalicLocationPath: String = "fonts/lato_italic.ttf"
    private val latoRegularLocationPath: String = "fonts/lato_regular.ttf"
    private val notoSansArBoldLocationPath: String = "fonts/noto_sans_arabic_bold.ttf"
    private val notoSansArRegularLocationPath: String = "fonts/noto_sans_arabic_regular.ttf"
    private val optimaBoldLocationPath: String = "fonts/optima_bold.ttf"
    private val optimaRegularLocationPath: String = "fonts/optima_regular.ttf"

    companion object {
        lateinit var geDinarOneMedium: Typeface
        lateinit var latoBold: Typeface
        lateinit var lotoBoldItalic: Typeface
        lateinit var latoItalic: Typeface
        lateinit var latoRegular: Typeface
        lateinit var notoSansArBold: Typeface
        lateinit var notoSansArRegular: Typeface
        lateinit var optimaBold: Typeface
        lateinit var optimaRegular: Typeface
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
        setupFonts()
    }

    private fun setupFonts() {
        geDinarOneMedium = Typeface.createFromAsset(assets, geDinarOneMediumLocationPath)
        latoBold = Typeface.createFromAsset(assets, latoBoldLocationPath)
        lotoBoldItalic = Typeface.createFromAsset(assets, lotoBoldItalicLocationPath)
        latoItalic = Typeface.createFromAsset(assets, latoItalicLocationPath)
        latoRegular = Typeface.createFromAsset(assets, latoRegularLocationPath)
        notoSansArBold = Typeface.createFromAsset(assets, notoSansArBoldLocationPath)
        notoSansArRegular = Typeface.createFromAsset(assets, notoSansArRegularLocationPath)
        optimaBold = Typeface.createFromAsset(assets, optimaBoldLocationPath)
        optimaRegular = Typeface.createFromAsset(assets, optimaRegularLocationPath)
    }
}