package org.tracker.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_horse.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.tracker.models.HorseModel
import org.tracker.R
import org.tracker.helpers.readImage
import org.tracker.helpers.readImageFromPath
import org.tracker.helpers.showImagePicker
import org.tracker.main.MainApp
import org.tracker.models.Location


class HorseActivity : AppCompatActivity(), AnkoLogger, AdapterView.OnItemSelectedListener {

    var horse = HorseModel()
    lateinit var app: MainApp
    var edit = false
    private val IMAGE_REQUEST = 1
    val LOCATION_REQUEST = 2
    //var location = Location(52.245696, -7.139102, 15f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horse)
        app = application as MainApp

        //setting the toolbar and adding title
        //using 'apply' scope function, instead of having to use the object name every time, we can access the methods associated with it inside it
        setSupportActionBar(toolbarAdd).apply { title = getString(R.string.add_horse) }

        val adapter = ArrayAdapter.createFromResource(this, R.array.horse_sex, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)
        horseSexSpinner.adapter=adapter
        horseSexSpinner.onItemSelectedListener=this

//   getting the details of the horse to update
        if (intent.hasExtra("horse_edit")) {
            edit = true
            horse = intent.extras?.getParcelable<HorseModel>("horse_edit")!!
            horseTitle.setText(horse.title)
            horseBreeder.setText(horse.breeder)
            horseOwner.setText(horse.owner)
            horseTrainer.setText(horse.trainer)
            horseImage.setImageBitmap(readImageFromPath(this, horse.image))
            if (horse.image != null) {
                chooseImage.setText(R.string.change_horse_image)
                btnAdd.setText(R.string.save_horse)
            }
        }
        btnAdd.setText(R.string.save_horse)


        //Add/Update Horse Button
        btnAdd.setOnClickListener() {

            horse.title = horseTitle.text.toString()
            horse.breeder = horseBreeder.text.toString()
            horse.owner = horseOwner.text.toString()
            horse.trainer = horseTrainer.text.toString()

            //Data Validation to ensure no empty data can be set
            if (horse.title.isEmpty()) {
                toast("Please enter a horse title")
            } else if (horse.breeder.isEmpty()) {
                toast("Please enter a horse breeder")
            } else if (horse.owner.isEmpty()) {
                toast("Please enter a horse owner")
            } else if (horse.trainer.isEmpty()) {
                toast("Please enter a horse trainer")
            } else {
                if (edit) {
                    app.horses.update(horse.copy())
                } else {
                    app.horses.create(horse.copy())
                }
            }
            setResult(RESULT_OK)
            finish()
        }

        //Image Picker Button
        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)

        }

        //Location Picker Button
        racingLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)
            if (horse.zoom != 0f) {
                location.lat =  horse.lat
                location.lng = horse.lng
                location.zoom = horse.zoom
            }
            startActivityForResult(
                intentFor<MapsActivity>().putExtra("location", location),
                LOCATION_REQUEST
            )
        }
    }

    //Menu Icon
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_horse, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.horses.delete(horse)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //Image and Location Setter
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    horse.image = data.data.toString()
                    horseImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_horse_image)
                }
            }
            LOCATION_REQUEST -> {
                if (data != null) {
                    val location = data.extras?.getParcelable<Location>("location")!!
                    horse.lat = location.lat
                    horse.lng = location.lng
                    horse.zoom = location.zoom
                }
            }
        }
    }
/*//  when ever you select an item in the spinnner this method will be called and we need to handle the code inside*/
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        horse.sex = parent?.getItemAtPosition(position).toString() //getting the value which we select form the spinner and converting it to String to store inside horse.sex variable
    (parent?.getChildAt(0) as TextView).setTextColor(getColor(R.color.text_color)) // get the 0th index of the spinner as TextView and set the color
}

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}
