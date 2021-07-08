package com.atech.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import androidx.viewpager.widget.ViewPager
import com.abrahamlay.base.presentation.TabAdapter
import com.atech.base.viewmodel.BaseViewModel
import com.google.android.material.tabs.TabLayout

/**
 * Created by Abraham Lay on 2020-06-09.
 */
abstract class TabFragment<VB : ViewBinding, VM : BaseViewModel> : BaseFragment<VB, VM>() {

    protected var tabLayout: TabLayout? = null
    protected var pager: ViewPager? = null

    private var adapter: TabAdapter? = null
    protected lateinit var fragments: MutableList<Fragment>
    protected lateinit var titles: MutableList<String>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_tab, container, false)
        tabLayout = view.findViewById(R.id.tab)
        pager = view.findViewById(R.id.pager)
        return view
    }

    override fun onInitViews() {
        super.onInitViews()
        fragments = mutableListOf()
        titles = mutableListOf()
    }

    protected fun initTabAndPager() {
        if (isAdded) {
            adapter = TabAdapter(
                fragments,
                titles,
                childFragmentManager
            )
            pager!!.adapter = adapter
            tabLayout!!.setupWithViewPager(pager)
        }
    }

}
