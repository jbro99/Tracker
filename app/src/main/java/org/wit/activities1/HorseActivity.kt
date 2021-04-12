package org.wit.activities1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_horse.*
import kotlinx.android.synthetic.main.activity_horse.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.horse.models.HorseModel
import org.wit.tracker.R


class HorseActivity : AppCompatActivity(), AnkoLogger {

    var horse = HorseModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horse)
        app = application as MainApp

        btnAdd.setOnClickListener() {
            horse.title = horseTitle.text.toString()
            horse.description = horseDescription.text.toString()
            if (horse.title.isNotEmpty()) {
                app.placemarks.add(horse.copy())
                info("add Button Pressed: $horseTitle")
                app.placemarks.forEach { info("add Button Pressed: ${it.title}, ${it.horseDescription}")}
            }
            else {
                toast ("Please Enter a title")
            }
        }
    }
}
