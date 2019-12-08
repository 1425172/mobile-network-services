package at.ac.tuwien.nsa.gr12.comparelocations.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import at.ac.tuwien.nsa.gr12.comparelocations.R
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.CellTower

class CellTowerAdapter(private val cellTowers: List<CellTower>) : RecyclerView.Adapter<CellTowerAdapter.ViewHolder>() {

    // holder class to hold reference
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //get view reference
        var cellTowerInfo: TextView = view.findViewById(R.id.information) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create view holder to hold reference
        return ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.list_entry, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //set values
        holder.cellTowerInfo.text =  R.string.cellId.toString()+" " +cellTowers[position].cellId.toString()
    }

    override fun getItemCount(): Int {
        return cellTowers.size
    }
}