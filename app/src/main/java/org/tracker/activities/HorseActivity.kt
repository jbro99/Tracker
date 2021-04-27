package org.tracker.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_horse.*
import kotlinx.android.synthetic.main.activity_horse.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.tracker.models.HorseModel
import org.tracker.R
import org.tracker.main.MainApp


class HorseActivity : AppCompatActivity(), AnkoLogger {

    var horse = HorseModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horse)
        app = application as MainApp

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        if (intent.hasExtra("horse_edit")) {
            horse = intent.extras?.getParcelable<HorseModel>("horse_edit")!!
            horseTitle.setText(horse.title)
            horseDescription.setText(horse.description)
            btnAdd.setText(R.string.save_horse)
        }

        btnAdd.setOnClickListener() {
            horse.title = horseTitle.text.toString()
            horse.description = horseDescription.text.toString()
            if (horse.title.isNotEmpty()) {
                app.horses.create(horse.copy())
                info("add Button Pressed: ${horse}")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            } else {
                toast(R.string.enter_horse_title)
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_horse, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
