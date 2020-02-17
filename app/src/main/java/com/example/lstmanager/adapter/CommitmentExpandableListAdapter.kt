package com.example.lstmanager.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.core.view.marginStart
import com.example.lstmanager.R


class CommitmentExpandableListAdapter(
    val context: Context,
    val expandableListTitle: List<String>,
    val expandableListDetail: HashMap<String, ArrayList<String>>
): BaseExpandableListAdapter() {

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return expandableListDetail[expandableListTitle[groupPosition]]!![childPosition]
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val expandedListText = getChild(groupPosition, childPosition).toString()
        var view = convertView
        val viewHolder: ChildViewHolder

        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.commessa_item_layout, parent, false)

            viewHolder = ChildViewHolder()
            viewHolder.txtCommitmentName = view!!.findViewById<View>(R.id.txtCommitmentName) as TextView
        } else {
            //no need to call findViewById, can use existing ones from saved view holder
            viewHolder = view.tag as ChildViewHolder
        }

        viewHolder.txtCommitmentName!!.text = expandedListText
        viewHolder.txtCommitmentName!!.textSize = 25F
        viewHolder.txtCommitmentName!!.setTextColor(Color.GRAY)


        viewHolder.txtCommitmentName!!.setPadding(130, 50,50,50)


        view.tag = viewHolder

        return view
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return expandableListDetail[expandableListTitle[groupPosition]]!!.size
    }


    override fun getGroup(listPosition: Int): Any? {
        return expandableListTitle[listPosition]
    }

    override fun getGroupCount(): Int {
        return expandableListTitle.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val listTitle = getGroup(groupPosition) as String?
        var view = convertView
        val viewHolder: GroupViewHolder

        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.commitment_list_header, parent, false)

            viewHolder = GroupViewHolder()
            viewHolder.txtCommitmentName = view!!.findViewById<View>(R.id.listTitle) as TextView
        } else {
            //no need to call findViewById, can use existing ones from saved view holder
            viewHolder = view.tag as GroupViewHolder
        }

        viewHolder.txtCommitmentName!!.text = listTitle
        viewHolder.txtCommitmentName!!.textSize = 25F
        viewHolder.txtCommitmentName!!.setTextColor(Color.BLACK)

        viewHolder.txtCommitmentName!!.setTypeface(null, Typeface.BOLD)
        viewHolder.txtCommitmentName!!.setPadding(130, 50,50,50)

        view.tag = viewHolder
        
        return view
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }

    //view holder is used to prevent findViewById calls
    private class GroupViewHolder {
        internal var txtCommitmentName: TextView? = null
    }

    private class ChildViewHolder {
        internal var txtCommitmentName: TextView? = null
    }
}