package org.tracker.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.tracker.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { //creates the splash screen
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)//

        //loading the animation
        val fadeIn = AnimationUtils.loadAnimation(this,R.anim.fade_in)
        val zoomIn = AnimationUtils.loadAnimation(this,R.anim.zoom_in)

        //setting the animation to views
        tv_welcome.animation = fadeIn
        img_icon.animation = zoomIn

        //going to delay the splash screen for 1000 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            val intent=Intent(this, HorseListActivity::class.java)
            startActivity(intent) //calling the intent method
            finish()// will be terminated
        }, 1000)// will come to activity file within a few seconds

    }

}