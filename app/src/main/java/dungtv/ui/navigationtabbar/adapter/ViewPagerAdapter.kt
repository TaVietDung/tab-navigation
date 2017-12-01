package dungtv.ui.navigationtabbar.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dungtv.ui.navigationtabbar.R

class ViewPagerAdapter(private val mContext: Context) : PagerAdapter() {
    override fun getCount(): Int {
        return 5
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: View?, position: Int, `object`: Any?) {
        (container as ViewPager).removeView(`object` as View?)
    }

    @SuppressLint("InflateParams")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(
                mContext).inflate(R.layout.item_vp_list, null, false)

        val recyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(
                mContext, LinearLayoutManager.VERTICAL, false
        )
        recyclerView.adapter = RecycleAdapter(mContext)

        container.addView(view)
        return view
    }
}