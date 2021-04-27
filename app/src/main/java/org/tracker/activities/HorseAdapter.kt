package org.tracker.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_horse.view.*
import kotlinx.android.synthetic.main.card_horse.view.*
import org.tracker.R
import org.tracker.models.HorseModel
interface HorseListener {
    fun onHorseClick(horse: HorseModel)
}

class HorseAdapter constructor(private var horses: List<HorseModel>,
                               private val listener: HorseListener) : RecyclerView.Adapter<HorseAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.card_horse,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val horse = horses[holder.adapterPosition]
        holder.bind(horse, listener)
    }

    override fun getItemCount(): Int = horses.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(horse: HorseModel, listener : HorseListener) {
            itemView.horseTitleC.text = horse.title
            itemView.horseDesc.text = horse.description
            itemView.setOnClickListener { listener.onHorseClick(horse) }
        }
    }
    }

