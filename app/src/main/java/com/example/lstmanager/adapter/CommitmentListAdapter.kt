package com.example.lstmanager.adapter

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lstmanager.R
import com.example.lstmanager.objects.Commitment

class CommitmentListAdapter(
    private val commitmentList: ArrayList<Commitment>,
    ctx: Context
): ArrayAdapter<Commitment>(ctx, R.layout.commessa_item_layout, commitmentList) {

    //view holder is used to prevent findViewById calls
    private class ItemViewHolder {
        internal var txtCommitmentName: TextView? = null
        internal var txtCommitmentNumber: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        val viewHolder: ItemViewHolder

        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.commessa_item_layout, parent, false)

            viewHolder = ItemViewHolder()
            viewHolder.txtCommitmentName = view!!.findViewById<View>(R.id.txtCommitmentName) as TextView
            viewHolder.txtCommitmentNumber = view.findViewById<View>(R.id.txtCommitmentNumber) as TextView
        } else {
            //no need to call findViewById, can use existing ones from saved view holder
            viewHolder = view.tag as ItemViewHolder
        }

        val commitment = getItem(position)
        viewHolder.txtCommitmentName!!.text = commitment!!.getName()
        viewHolder.txtCommitmentNumber!!.text = commitment.getNumber()

        view.tag = viewHolder

        return view

    }
}