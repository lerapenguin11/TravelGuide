package com.example.travelguide.presentation.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.travelguide.R
import com.example.travelguide.presentation.MainActivity
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class OnBoadrdingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boadrding)

        setViewPager()
    }

    private fun setViewPager() {
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(ThirstFragment())
        fragmentList.add(SecondFragment())

        val adapterViewPager = OnBoardingFootballAdapter(
            fragmentList,
            this.supportFragmentManager,
            lifecycle
        )
        val finish = findViewById<ImageView>(R.id.finish)

        finish.setOnClickListener { v: View? ->
            val intent = Intent(
                this@OnBoadrdingActivity,
                MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = adapterViewPager
        val indicator = findViewById<DotsIndicator>(R.id.dots_indicator)
        indicator.attachTo(viewPager)
    }
}