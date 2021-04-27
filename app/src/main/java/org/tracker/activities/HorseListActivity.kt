package org.tracker.activities

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_horse_list.*
import org.jetbrains.anko.intentFor
import org.tracker.R
import org.tracker.main.MainApp
import org.jetbrains.anko.startActivityForResult
import org.tracker.models.HorseModel


class HorseListActivity : AppCompatActivity(), HorseListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horse_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        //recyclerView.adapter = HorseAdapter(app.horses)
        recyclerView.adapter = HorseAdapter(app.horses.findAll(), this)

        toolbarhorse.title = title
       setSupportActionBar(toolbarhorse)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<HorseActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onHorseClick(horse: HorseModel) {
        startActivityForResult(intentFor<HorseActivity>().putExtra("horse_edit", horse), 0)
    }
}


