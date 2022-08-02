package com.developer.u_glow.view.activity.home

import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.base.app.utils.navigateNew
import com.base.app.utils.navigateTo
import com.base.app.view.BaseActivity
import com.developer.u_glow.R
import com.developer.u_glow.databinding.ActivityHomeBinding
import com.developer.u_glow.util.Constants
import com.developer.u_glow.util.Dialog
import com.developer.u_glow.viewmodel.home.HomeViewModel

class HomeActivity(override val layoutId: Int = R.layout.activity_home) :
    BaseActivity<HomeViewModel, ActivityHomeBinding>() {


    override val mViewModel: HomeViewModel by viewModels()
    private var navController: NavController? = null
    private var currentFragment: String? = null


    override fun onInitialize() {
        mViewModel.isCustomer = sharedPreferences.isCustomer
        mViewBinding.includeBody.view = this
        mViewBinding.includeNav.view = this
        navController = findNavController(R.id.fvHome)
        navController?.addOnDestinationChangedListener(destinationListener)
        navController?.setGraph(if (sharedPreferences.isCustomer!!) R.navigation.nav_graph else R.navigation.nav_graph_pro)
        setupBottomNavigation()
        configureBackListener()


    }

    private fun configureBackListener() {
        mViewBinding.includeBody.tvBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupBottomNavigation() {
        mViewBinding.includeBody.bottomNavigationView.setOnNavigationItemSelectedListener {
            mViewModel.currentFragment = it.itemId
            performNavigation()
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun navigateToDashboard() {
        navController?.navigateNew(
            if (mViewModel.isCustomer!!) R.id.nav_dashboard_fragment else R.id.nav_dashboard_pro_fragment,
            if (mViewModel.isCustomer!!) R.id.nav_graph else R.id.nav_graph_pro
        )
    }

    private fun navigateToBooking() {
        navController?.navigateNew(
            if (mViewModel.isCustomer!!) R.id.nav_booking_fragment else R.id.nav_open_glow_fragment,
            if (mViewModel.isCustomer!!) R.id.nav_graph else R.id.nav_graph_pro
        )
    }

    private fun navigateToAbout() {
        navController?.navigateNew(
            R.id.nav_about_fragment,
            if (mViewModel.isCustomer!!) R.id.nav_graph else R.id.nav_graph_pro
        )
    }

    private fun navigateToFaq() {
        navController?.navigateNew(
            R.id.nav_faq_fragment,
            if (mViewModel.isCustomer!!) R.id.nav_graph else R.id.nav_graph_pro
        )
    }

    private fun navigateToContact() {
        navController?.navigateNew(
            R.id.nav_contact_fragment,
            if (mViewModel.isCustomer!!) R.id.nav_graph else R.id.nav_graph_pro
        )
    }

    private fun performNavigation() {
        when (mViewModel.currentFragment) {
            R.id.dashboard -> navigateToDashboard()

            R.id.booking -> navigateToBooking()

            R.id.settings -> {
                if (mViewModel.isCustomer!!)
                    navController?.navigateNew(R.id.nav_setting_fragment, R.id.nav_graph)
                else
                    navController?.navigateNew(R.id.nav_setting_pro_fragment, R.id.nav_graph_pro)

            }

            R.id.openGlow -> {
                if (mViewModel.isCustomer!!)
                    navController?.navigateNew(R.id.nav_pick_category_fragment, R.id.nav_graph)
            }

            R.id.notification -> {
                if (mViewModel.isCustomer!!)
                    navController?.navigateNew(R.id.nav_notification_fragment, R.id.nav_graph)
                else
                    navController?.navigateNew(
                        R.id.nav_notification_pro_fragment,
                        R.id.nav_graph_pro
                    )
            }
//            else -> navController?.navigate(R.id.action_to_pick_category)
        }
    }

    override fun subscribeObservers() {
        /* mViewModel.stateObserver.observe(this, Observer {
             when (it) {

             }
         })*/
    }

    fun onClickOpenDrawer() {
        mViewBinding.drawerLayout.openDrawer(GravityCompat.END)
    }

    fun onClickCloseDrawer() {
        mViewBinding.drawerLayout.closeDrawer(GravityCompat.END)
    }

    override fun onBackPressed() {
        if (mViewBinding.drawerLayout.isDrawerOpen(GravityCompat.END)) {
            mViewBinding.drawerLayout.closeDrawer(GravityCompat.END)
        } else if (!navController?.popBackStack()!!) {
            super.onBackPressed()
        }
    }

    fun onClickNavigationMenu(position: Int) {
        onClickCloseDrawer()
        when (position) {
            Constants.Count.zero -> navigateToDashboard()
            Constants.Count.two -> navigateToContact()
            Constants.Count.three -> navigateToBooking()
            Constants.Count.four -> navigateToAbout()
            Constants.Count.five -> navigateToFaq()
        }
    }

    private val destinationListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            currentFragment = destination.label as String?
            when (currentFragment) {
                Constants.Fragment.dashboard -> {
                    mViewBinding.includeBody.isBlack = true
                    mViewBinding.includeBody.tvBack.visibility = View.GONE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.white)
                }

                Constants.Fragment.pickCategory -> {
                    mViewBinding.includeBody.isBlack = true
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.white)
                }

                Constants.Fragment.pickSubCategory -> {
                    mViewBinding.includeBody.isBlack = true
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.white)

                }

                Constants.Fragment.selectGlow -> {
                    mViewBinding.includeBody.isBlack = true
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.white)

                }

                Constants.Fragment.details -> {
                    mViewBinding.includeBody.isBlack = true
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.white)

                }

                Constants.Fragment.glowPosted -> {
                    mViewBinding.includeBody.isBlack = false
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.drawable.bg_congrats_page)

                }

                Constants.Fragment.booking -> {
                    mViewBinding.includeBody.isBlack = false
                    mViewBinding.includeBody.tvBack.visibility = View.GONE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.orange)
                }

                Constants.Fragment.bookingDetail -> {
                    mViewBinding.includeBody.isBlack = true
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.white)
                }

                Constants.Fragment.openGlow -> {
                    mViewBinding.includeBody.isBlack = false
                    mViewBinding.includeBody.tvBack.visibility = View.GONE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.orange)
                }

                Constants.Fragment.makeOffers -> {
                    mViewBinding.includeBody.isBlack = true
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.white)
                }

                Constants.Fragment.glowAccept -> {
                    mViewBinding.includeBody.isBlack = false
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.drawable.bg_congrats_page)
                }

                Constants.Fragment.glowDecline -> {
                    mViewBinding.includeBody.isBlack = false
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.orange)
                }

                Constants.Fragment.dashboardPro -> {
                    mViewBinding.includeBody.isBlack = true
                    mViewBinding.includeBody.tvBack.visibility = View.GONE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.white)
                }

                Constants.Fragment.profile -> {
                    mViewBinding.includeBody.isBlack = true
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.white)
                }

                Constants.Fragment.service -> {
                    mViewBinding.includeBody.isBlack = false
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.green)
                }

                Constants.Fragment.addQualification -> {
                    mViewBinding.includeBody.isBlack = false
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.pink)
                }

                Constants.Fragment.gallery -> {
                    mViewBinding.includeBody.isBlack = false
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.orange)
                }

                Constants.Fragment.editProfilePro -> {
                    mViewBinding.includeBody.isBlack = true
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.white)
                }

                Constants.Fragment.yourProfile -> {
                    mViewBinding.includeBody.isBlack = true
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.white)
                }

                Constants.Fragment.setting -> {
                    mViewBinding.includeBody.isBlack = true
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.white)
                }

                Constants.Fragment.settingPro -> {
                    mViewBinding.includeBody.isBlack = true
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.white)
                }

                Constants.Fragment.settingPassportPro -> {
                    mViewBinding.includeBody.isBlack = true
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.white)
                }

                Constants.Fragment.about -> {
                    mViewBinding.includeBody.isBlack = false
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.drawable.bg_about_us)
                }

                Constants.Fragment.faq -> {
                    mViewBinding.includeBody.isBlack = true
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.white)
                }

                Constants.Fragment.contact -> {
                    mViewBinding.includeBody.isBlack = false
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.green)
                }

                Constants.Fragment.analytics -> {
                    mViewBinding.includeBody.isBlack = true
                    mViewBinding.includeBody.tvBack.visibility = View.VISIBLE
                    mViewBinding.includeBody.clParent.background =
                        ContextCompat.getDrawable(this, R.color.white)
                }
            }
        }

    override fun onResume() {
        super.onResume()
        navController?.addOnDestinationChangedListener(destinationListener)
    }

    override fun onPause() {
        super.onPause()
        navController?.removeOnDestinationChangedListener(destinationListener)
    }

    fun onClickUser(){
        navController?.navigateTo(R.id.nav_your_profile)
    }

}