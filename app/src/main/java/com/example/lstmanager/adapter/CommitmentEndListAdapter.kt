package com.example.lstmanager.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.lstmanager.R
import com.example.lstmanager.objects.Commitment
import com.example.lstmanager.objects.Job

class CommitmentEndListAdapter(
    private val elementsList: ArrayList<Job>,
    ctx: Context
): ArrayAdapter<Job>(ctx, R.layout.commessa_end_item_layout, elementsList) {
    //view holder is used to prevent findViewById calls
    class ItemViewHolder {
        internal var txtCommitmentName: TextView? = null
        internal var txtCommitmentEndWork: TextView? = null
        internal var checkBox: CheckBox? = null
        var job: Job? = null
        var index: Int = -1
    }

    var listOfItems = ArrayList<ItemViewHolder>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        val viewHolder: ItemViewHolder

        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.commessa_end_item_layout, parent, false)

            viewHolder = ItemViewHolder()
            viewHolder.txtCommitmentName = view!!.findViewById<View>(R.id.txtCommitmentEndName) as TextView
            viewHolder.txtCommitmentEndWork = view.findViewById<View>(R.id.txtCommitmentEndWork) as TextView
            viewHolder.checkBox = view.findViewById<View>(R.id.checkBox) as CheckBox
            viewHolder.index = position
            listOfItems.add(viewHolder)

            val itemView = view.findViewById<LinearLayout>(R.id.commessaItemEnd)
            itemView.setOnClickListener{ v ->
                val cb = v.findViewById<CheckBox>(R.id.checkBox)
                cb.isChecked = !cb.isChecked
            }

        } else {
            //no need to call findViewById, can use existing ones from saved view holder
            viewHolder = view.tag as ItemViewHolder
        }

        val element = getItem(position)
        viewHolder.txtCommitmentName!!.text = element!!.commitment
        viewHolder.txtCommitmentEndWork!!.text = element.work
        viewHolder.job = element

        view.tag = viewHolder

        return view
    }

    fun checkItemTrue(position: Int){
        listOfItems[position].checkBox!!.isChecked = true
    }

    fun checkItemFalse(position: Int){
        listOfItems[position].checkBox!!.isChecked = false
    }

    fun getSelectedJobs(): ArrayList<Job>{
        var ret = ArrayList<Job>()
        listOfItems.forEach { item ->
            if (item.checkBox!!.isChecked){
                ret.add(item.job!!)
            }
        }
        return ret
    }
}