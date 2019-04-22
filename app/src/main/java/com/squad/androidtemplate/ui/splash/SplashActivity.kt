package com.squad.androidtemplate.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.squad.androidtemplate.R
import com.squad.androidtemplate.ui.MainActivity

class SplashActivity : AppCompatActivity() {


    internal var handler = Handler()
    internal var isInBackground = false
    internal val SPLASH_SCREEN_DELAY_TIME: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        startEnteringAnimation()
        Log.v("dsdsdsds", "dsdsdsds")
        handler.postDelayed({
            if (!isInBackground) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                overridePendingTransition(0, 0)
            }
        }, (SPLASH_SCREEN_DELAY_TIME))
        startExitAnimation()
    }

    private fun startExitAnimation() {
    }

    private fun startEnteringAnimation() {
    }

    override fun onRestart() {
        super.onRestart()
        isInBackground = false
        handler.postDelayed({
            if (!isInBackground) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }, SPLASH_SCREEN_DELAY_TIME / 2)
    }

    override fun onStop() {
        super.onStop()
        isInBackground = true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        isInBackground = true
    }

}
