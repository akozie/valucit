package com.twoam.valucit.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.twoam.valucit.R
import com.twoam.valucit.adapter.OnBoardingViewPagerAdapter
import com.twoam.valucit.databinding.ActivityLandingBinding
import com.twoam.valucit.model.onboarding.OnBoardingData
import java.util.ArrayList

class LandingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandingBinding

    private lateinit var skipClick: TextView
    private lateinit var sliderDot: TabLayout
    private var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    private var onBoardingViewPager: ViewPager? = null
    private lateinit var button: Button
    private lateinit var getStartedButton: Button
    private var position = 0
    private lateinit var onBoardingDataList: MutableList<OnBoardingData>
    private lateinit var buttonAnimation : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Valucit)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_landing)

        //The activity on full screen
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //when this activity is about to be launched we need to check if
        // the activity has been seen by the user
//        if(restorePrefData()){
//            val intent= Intent(applicationContext, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }y
        setContentView(binding.root)

        // initializing the views
        skipClick = binding.onBoardingActivitySkipTextView
        onBoardingViewPager = binding.screenPager
        sliderDot = binding.onBoardingActivityTabLayout
        button = binding.activityNextButton
        getStartedButton = binding.activityGetStartedButton
        buttonAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.button_animation)

        skipClick.setOnClickListener {
            startNewActivity()
        }
        getStartedButton.setOnClickListener {
            startNewActivity()
        }

        setOnBoardingViewPagerAdapter()

        // Next button click listener
        button.setOnClickListener {
            position = onBoardingViewPager!!.currentItem
            if (position < onBoardingDataList.size) {
                position++
                onBoardingViewPager!!.currentItem = position
            }
            if(position == onBoardingDataList.size - 1) { //when we reach the last screen
                loadLastScreen()
            }
        }

        //Tab indicator listener
        sliderDot.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab!!.position == onBoardingDataList.size - 1){
                    loadLastScreen()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }

//    private fun restorePrefData(): Boolean {
//        val pref: SharedPreferences = application.getSharedPreferences("MyPref", MODE_PRIVATE)
//        val isIntroActivitySeenBefore: Boolean = pref.getBoolean("IsIntroActivityOpened", false)
//        return isIntroActivitySeenBefore
//
//    }

    private fun startNewActivity() {
        //open new activity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        //we need to save a value to storage so next time the user run the app
        // we could know that he already checked the intro activity
        // I will will use shared preferences for this.
        //sharedPrefsData()
        finish()

    }

//    private fun sharedPrefsData() {
//        val pref: SharedPreferences = application.getSharedPreferences("MyPref", MODE_PRIVATE)
//        val editor: SharedPreferences.Editor = pref.edit()
//        editor.putBoolean("IsIntroActivityOpened", true)
//        editor.commit()
//    }

    // create a function outside the onCreate to bind the variables to the actual views
    private fun setOnBoardingViewPagerAdapter() {
        onBoardingDataList = ArrayList()
        onBoardingDataList.add(OnBoardingData("Lorem ipsum dolor sit amet, consectetur adipiscing eli", R.drawable.onboarding_1))
        onBoardingDataList.add(OnBoardingData("Lorem ipsum dolor sit amet, consectetur adipiscing eli",  R.drawable.onboarding_1))
        onBoardingDataList.add(OnBoardingData("Lorem ipsum dolor sit amet, consectetur adipiscing eli",  R.drawable.onboarding_1))

        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingDataList)
        onBoardingViewPager!!.adapter = onBoardingViewPagerAdapter
        sliderDot.setupWithViewPager(onBoardingViewPager, true)
    }

    private fun loadLastScreen() {
        button.visibility = View.INVISIBLE
        getStartedButton.visibility = View.VISIBLE
        sliderDot.visibility = View.INVISIBLE
        skipClick.visibility = View.INVISIBLE

        //animate the button
        getStartedButton.animation = buttonAnimation

    }



}