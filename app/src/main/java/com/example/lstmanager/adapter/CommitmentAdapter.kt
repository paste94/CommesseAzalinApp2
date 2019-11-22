package com.example.lstmanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lstmanager.R
import com.example.lstmanager.objects.Commitment

class CommitmentAdapter(private val commitmentList: ArrayList<Commitment>):  RecyclerView.Adapter<CommitmentAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtCommitmentName.text = commitmentList[position].getName()
        holder.txtCommitmentNumber.text = commitmentList[position].getNumber()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.commessa_item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return commitmentList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtCommitmentName: TextView = itemView.findViewById(R.id.txtCommitmentName)
        val txtCommitmentNumber: TextView = itemView.findViewById(R.id.txtCommitmentNumber)

    }
}