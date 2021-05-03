package org.tracker.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_horse_list.*
import org.tracker.R
import org.tracker.main.MainApp

class WelcomeActivity: AppCompatActivity(){
 lateinit var app: MainApp

override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.welcome)
       app = application as MainApp

      // enables action bar and set title
       toolbar.title = title
       setSupportActionBar(toolbar)
    }
}
