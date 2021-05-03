package org.tracker.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HorseModel(var id: Long = 0,
                      var title: String = "",
                      var breeder: String = "",
                      var owner: String = "",
                      var sex : String= "",
                      var age : Int = 0,
                      var trainer : String = "",
                      var image: String = "",
                      var lat : Double = 0.0,
                      var lng: Double = 0.0,
                      var zoom: Float = 0f) : Parcelable
