package dungtv.ui.navigationtabbar.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dungtv.ui.navigationtabbar.R

class RecycleAdapter(private val mContext: Context) : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.mTextView?.text = String.format("Navigation Item #%d", position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 20
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mTextView: TextView = itemView.findViewById<View>(R.id.tvName) as TextView

    }
}