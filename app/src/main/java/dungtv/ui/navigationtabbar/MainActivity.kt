package dungtv.ui.navigationtabbar

import android.app.Activity
import android.graphics.Color
import android.support.design.widget.Snackbar
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import dungtv.ui.navigationtabbar.adapter.ViewPagerAdapter
import dungtv.ui.navigationtabbar.widget.NavigationTabBar
import dungtv.ui.navigationtabbar.widget.OnTabBarSelectedIndexListener

import java.util.ArrayList
import java.util.Random
import kotlinx.android.synthetic.main.activity_main.mViewPager
import kotlinx.android.synthetic.main.activity_main.mCoordinatorLayout
import kotlinx.android.synthetic.main.activity_main.mFab
import kotlinx.android.synthetic.main.activity_main.mToolbar

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI() {
        mViewPager.adapter = ViewPagerAdapter(applicationContext)

        val colors = resources.getStringArray(R.array.default_preview)

        val navigationTabBar = findViewById<View>(R.id.ntb_horizontal) as NavigationTabBar

        navigationTabBar.models = initModelTabBar(colors)
        navigationTabBar.setViewPager(mViewPager, 0)

        //IMPORTANT: ENABLE SCROLL BEHAVIOUR IN COORDINATOR LAYOUT
        navigationTabBar.isBehaviorEnabled = true

        initAction(navigationTabBar)
        initToolBar()
    }

    private fun initModelTabBar(colors: Array<String>): ArrayList<NavigationTabBar.Model> {
        val models = ArrayList<NavigationTabBar.Model>()
        models.add(
                NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(this, R.drawable.ic_first),
                        Color.parseColor(colors[0]))
                        .title("Heart")
                        .build()
        )
        models.add(
                NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(this, R.drawable.ic_second),
                        Color.parseColor(colors[1]))
                        .title("Cup")
                        .build()
        )
        models.add(
                NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(this, R.drawable.ic_third),
                        Color.parseColor(colors[2]))
                        .title("Diploma")
                        .build()
        )
        models.add(
                NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(this, R.drawable.ic_fourth),
                        Color.parseColor(colors[3]))
                        .title("Flag")
                        .build()
        )
        models.add(
                NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(this, R.drawable.ic_fifth),
                        Color.parseColor(colors[4]))
                        .title("Medal")
                        .build()
        )

        return models
    }

    private fun initAction(navigationTabBar: NavigationTabBar) {
        navigationTabBar.onTabBarSelectedIndexListener = object : OnTabBarSelectedIndexListener {
            override fun onStartTabSelected(model: NavigationTabBar.Model, index: Int) {}

            override fun onEndTabSelected(model: NavigationTabBar.Model, index: Int) {
                model.hideBadge()
            }
        }
        navigationTabBar.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        mFab.setOnClickListener {
            for (i in 0 until navigationTabBar.models.size) {
                val model = navigationTabBar.models[i]
                navigationTabBar.postDelayed({
                    val title = Random().nextInt(15).toString()
                    if (!model.isBadgeShowed) {
                        model.setBadgeTitle(title)
                        model.showBadge()
                    } else
                        model.updateBadgeTitle(title)
                }, (i * 100).toLong())
            }

            mCoordinatorLayout.postDelayed({
                val snackBar = Snackbar.make(navigationTabBar, "Coordinator NTB", Snackbar.LENGTH_SHORT)
                snackBar.view.setBackgroundColor(Color.parseColor("#9b92b3"))
                (snackBar.view.findViewById<View>(R.id.snackbar_text) as TextView)
                        .setTextColor(Color.parseColor("#423752"))
                snackBar.show()
            }, 1000)
        }
    }

    private fun initToolBar() {
        mToolbar.setExpandedTitleColor(Color.parseColor("#009F90AF"))
        mToolbar.setCollapsedTitleTextColor(Color.parseColor("#9f90af"))
    }

}
