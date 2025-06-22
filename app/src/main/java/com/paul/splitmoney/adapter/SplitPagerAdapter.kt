package com.paul.splitmoney.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.paul.splitmoney.fragment.EquallyFragment
import com.paul.splitmoney.fragment.UnequallyFragment

class SplitPagerAdapter(fragment:Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EquallyFragment()
            1 -> UnequallyFragment()
            else -> Fragment()//throw IllegalArgumentException("Invalid tab index")
        }
    }
}