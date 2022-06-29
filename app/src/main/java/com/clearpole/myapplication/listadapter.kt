package com.clearpole.myapplication

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val list : MutableList<LyricAdapter>) :
    RecyclerView.Adapter<RecyclerAdapter.SetView>(){
    private lateinit var mContext: Context
    private var lastId : TextView?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetView {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.lyric_item, parent, false)
        mContext = parent.context
        return SetView(view)
    }

    override fun getItemCount(): Int = list.size

    class SetView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text : TextView
        get() = itemView.findViewById(R.id.lyric_item_text)
        val root : LinearLayout
        get() = itemView.findViewById(R.id.root_lrc)
    }

    override fun onBindViewHolder(holder: SetView, position: Int) {
        val item : LyricAdapter = list[position]
        holder.text.text = item.thisLine
        val textView = TextView(this.mContext)
        textView.text = item.id.toString()
        textView.id = item.id
        textView.text = item.thisLine
        textView.textSize = 23F
        textView.setTextColor(Color.parseColor("#50000000"))
        holder.root.addView(textView)
        holder.root.setOnClickListener{
            if (lastId?.id == textView.id){

            }else {
                textView.setTextColor(Color.parseColor("#FF000000"))
                lastId?.setTextColor(Color.parseColor("#50000000"))
                lastId = textView
            }
            println(position)
        }
    }
}
