package org.tracker.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_horse.view.*
import org.tracker.R
import org.tracker.helpers.readImageFromPath
import org.tracker.models.HorseModel

interface HorseListener {
    fun onHorseClick(horse: HorseModel)
}

class HorseAdapter constructor(
    private var horses: List<HorseModel>,
    private val listener: HorseListener
) : RecyclerView.Adapter<HorseAdapter.MainHolder>() {

    //inflating the card view into the view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_horse,
                parent,
                false
            )
        )
    }

    //binding the data into the view holder
    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val horse = horses[position]

        //method for binding the data
        holder.bind(horse, listener)
    }

    override fun getItemCount(): Int = horses.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //mapping the data to the view holder
        fun bind(horse: HorseModel, listener: HorseListener) {
            itemView.tv_horseTitle.text = horse.title
            itemView.tv_horseBreeder.text = horse.breeder
            itemView.tv_horseOwner.text = horse.owner
            itemView.tv_horseSex.text = horse.sex
            itemView.tv_horseTrainer.text = horse.trainer
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, horse.image))
            itemView.setOnClickListener { listener.onHorseClick(horse) }
        }
    }
}

