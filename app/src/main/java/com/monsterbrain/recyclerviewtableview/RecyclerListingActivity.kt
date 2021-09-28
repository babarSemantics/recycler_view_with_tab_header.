package com.monsterbrain.recyclerviewtableview

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class RecyclerListingActivity : AppCompatActivity() {
    private var responseItemList: MutableList<String>? = ArrayList()
    private var linearLayoutManager: LinearLayoutManager? = null
    private var menuListAdapter: MenuListAdapter? = null
    private var dailyCakeListScrollAdapter: DailyCakeListScrollAdapter? = null
    private var flagCheck = false
    private var checkCallBack = false
    private var checkScroll = true
    private var posSelected = 0
    private var totalCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_listing)
        setData()
    }

    private fun setData() {
        responseItemList?.add("test1")
        responseItemList?.add("test2")
        responseItemList?.add("test3")
        responseItemList?.add("test4")
        responseItemList?.add("test5")
        responseItemList?.add("test6")
        responseItemList?.add("test7")
        responseItemList?.add("test8")
        responseItemList?.add("test9")
        responseItemList?.add("test10")
        responseItemList?.add("test11")
        responseItemList?.add("test12")
        responseItemList?.add("test13")
        responseItemList?.add("test14")
        responseItemList?.add("test15")
        responseItemList?.add("test16")
        responseItemList?.add("test17")
        responseItemList?.add("test18")
        responseItemList?.add("test19")
        responseItemList?.add("test20")
        linearLayoutManager = LinearLayoutManager(this@RecyclerListingActivity, LinearLayoutManager.VERTICAL, false)
        linear_recycler_menuItem.layoutManager = linearLayoutManager
        menuListing.layoutManager = LinearLayoutManager(
            this@RecyclerListingActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        setTabs()
    }

    private fun setTabs() {
        for (aq in responseItemList!!.indices) {
            tabs!!.addTab(tabs!!.newTab().setText(responseItemList!![aq]))
            tabs!!.tabGravity = TabLayout.GRAVITY_FILL
        }
        setAdapter()
    }

    private fun setAdapter() {
        setMenuAdapter()
        initCategoryMenu()
    }

    fun bindWidgetsWithAnEvent() {
        tabs!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                checkCallBack = true
                posSelected = tab.position
                linear_recycler_menuItem!!.smoothScrollToPosition(tab.position)
                tabs!!.setSelectedTabIndicatorHeight((2 * resources.displayMetrics.density).toInt())
                selectionBackground(posSelected)
                if (tab.position == totalCount - 1) {
                    checkCallBack = false
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                checkCallBack = false
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                checkCallBack = true
                posSelected = tab.position
                selectionBackground(posSelected)
                linear_recycler_menuItem!!.smoothScrollToPosition(tab.position)
                tabs!!.setSelectedTabIndicatorHeight((2 * resources.displayMetrics.density).toInt())
                selectionBackground(posSelected)
                if (tab.position == totalCount - 1) {
                    checkCallBack = false
                }
            }
        })
    }

    private fun initCategoryMenu() {
        dailyCakeListScrollAdapter =
            DailyCakeListScrollAdapter(responseItemList!!)
        linear_recycler_menuItem!!.adapter = dailyCakeListScrollAdapter
        setScrollListener()
        bindWidgetsWithAnEvent()
    }

    private fun setScrollListener() {
        checkScroll = true
        linear_recycler_menuItem!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val visiblePosition = linearLayoutManager!!.findFirstVisibleItemPosition()
                val firstCompletelyVisiblePosition =
                    linearLayoutManager!!.findFirstCompletelyVisibleItemPosition()
                totalCount = linearLayoutManager!!.itemCount
                checkScroll = false
                if (visiblePosition > -1) {
                    if (!checkCallBack) {
                        if (firstCompletelyVisiblePosition == -1) {
                            selectionBackground(visiblePosition)
                            tabs!!.setScrollPosition(visiblePosition, 0f, true)
                        } else {
                            selectionBackground(firstCompletelyVisiblePosition)
                            tabs!!.setScrollPosition(firstCompletelyVisiblePosition, 0f, true)
                        }
                    } else {
                        selectionBackground(posSelected)
                        tabs!!.setScrollPosition(posSelected, 0f, true)
                        if (firstCompletelyVisiblePosition == posSelected) {
                            checkCallBack = false
                        }
                    }
                }
            }
        })
    }

    private fun selectionBackground(position: Int) {
        for (i in responseItemList!!.indices) {
            if (i == position) {
                val tabLayout =
                    (tabs!!.getChildAt(0) as ViewGroup).getChildAt(position) as LinearLayout
                val tabTextView = tabLayout.getChildAt(1) as TextView
                if (tabTextView != null) {
                    tabTextView.setTextColor(Color.parseColor("#ffffff"))
                    tabTextView.setPadding(11, 7, 11, 7)
                    tabTextView.setBackgroundResource(R.drawable.rounded_all_side_pink)
                }
                val tab = (tabs!!.getChildAt(0) as ViewGroup).getChildAt(position)
                val p = tab.layoutParams as ViewGroup.MarginLayoutParams
                p.setMargins(0, 0, 0, 0)
                tab.requestLayout()
                tabs!!.isSmoothScrollingEnabled = true
                tabs!!.setScrollPosition(position, 0f, true)
            } else {
                val tabLayout = (tabs!!.getChildAt(0) as ViewGroup).getChildAt(i) as LinearLayout
                val tabTextView = tabLayout.getChildAt(1) as TextView
                if (tabTextView != null) {
                    tabTextView.setPadding(11, 7, 11, 7)
                    tabTextView.setTextColor(Color.parseColor("#000000"))
                    tabTextView.setBackgroundResource(R.drawable.rounded_all_side_transparent)
                }
                val tab = (tabs!!.getChildAt(0) as ViewGroup).getChildAt(i)
                val p = tab.layoutParams as ViewGroup.MarginLayoutParams
                p.setMargins(0, 0, 0, 0)
                tab.requestLayout()
            }
        }
    }

    private fun setMenuAdapter() {
        menuListAdapter = MenuListAdapter(
            responseItemList!!,
            object : MenuListAdapter.OnItemClickListener {
                override fun onItemClick(position: Int, check: Boolean) {
                    checkCallBack = check
                    posSelected = position
                    mTransparent!!.visibility = View.GONE
                    card_menu_list!!.visibility = View.GONE
                    flagCheck = false
                    tabs!!.isSmoothScrollingEnabled = true
                    selectionBackground(position)
                    tabs!!.setScrollPosition(position, 0f, true)
                    linear_recycler_menuItem!!.smoothScrollToPosition(position)
                }
            })
        menuListing!!.adapter = menuListAdapter
        menu_btn!!.setOnClickListener {
            if (!flagCheck) {
                checkCallBack = false
                mTransparent!!.visibility = View.VISIBLE
                card_menu_list!!.visibility = View.VISIBLE
                flagCheck = true
            } else {
                checkCallBack = false
                mTransparent!!.visibility = View.GONE
                card_menu_list!!.visibility = View.GONE
                flagCheck = false
            }
        }
    }
}