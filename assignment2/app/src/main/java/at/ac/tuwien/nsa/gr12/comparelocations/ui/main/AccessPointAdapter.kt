package at.ac.tuwien.nsa.gr12.comparelocations.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import at.ac.tuwien.nsa.gr12.comparelocations.R
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.AccessPoint
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.CellTower

class AccessPointAdapter(private val cellTowers: List<AccessPoint>) : RecyclerView.Adapter<AccessPointAdapter.ViewHolder>() {

    // holder class to hold reference
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //get view reference
        var accessPointInfo: TextView = view.findViewById(R.id.information) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create view holder to hold reference
        return ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.list_entry, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //set values
        holder.accessPointInfo.text = holder.itemView.context.getString(R.string.mac) +" "+ cellTowers[position].macAddress.toString()
    }

    override fun getItemCount(): Int {
        return cellTowers.size
    }
}