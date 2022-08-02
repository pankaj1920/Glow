package com.developer.u_glow.util

interface Constants {


    interface BundleKey {
        companion object {
            const val position = "position"
            const val isCustomer = "isCustomer"
            const val isShowProgress = "isShowProgress"
        }
    }

    interface Count {
        companion object {
            const val zero = 0
            const val one = 1
            const val two = 2
            const val three = 3
            const val four = 4
            const val five = 5
        }
    }

    interface Activity {
        companion object {
            const val onBoarding = "com.developer.u_glow.view.activity.onboarding.OnBoardActivity"
            const val home = "com.developer.u_glow.view.activity.home.HomeActivity"
            const val authenticate = "com.developer.u_glow.view.activity.authenticate.AuthenticateActivity"
            const val register = "com.developer.u_glow.view.activity.authenticate.RegisterActivity"


        }
    }

    interface HandlerTime{
        companion object {
            const val splashTime = 2000L
        }
    }

    interface GlowType{
        companion object {
            const val booking = 1
            const val openGlow = 2
        }
    }

    interface Fragment{
        companion object {
            const val dashboard = "Dashboard"
            const val pickCategory = "Pick_Category"
            const val pickSubCategory = "Pick_Sub_Category"
            const val booking = "Booking"
            const val bookingDetail = "Booking_Detail"
            const val selectGlow = "Select_Glow"
            const val details = "Details"
            const val glowPosted = "Glow_Posted"
            const val dashboardPro = "Dashboard_Pro"
            const val openGlow = "Open_Glow"
            const val makeOffers = "Make_Offers"
            const val glowAccept = "Glow_Accept"
            const val glowDecline = "Glow_Decline"
            const val profile = "Profile"
            const val service = "Service"
            const val addQualification = "AddQualification"
            const val gallery = "Gallery"
            const val notification = "Notification"
            const val editProfilePro = "EditProfilePro"
            const val yourProfile = "Your_Profile"
            const val setting = "Setting"
            const val settingPro = "Setting_Pro"
            const val settingPassportPro = "Setting_Passport_Pro"
            const val about = "About"
            const val faq = "Faq"
            const val contact = "Contact"
            const val analytics = "Analytics"
        }
    }

    interface OptionType{
        companion object {
            const val profile = 0
            const val analytics = 2
            const val settings = 4
        }
    }

    interface Platform{
        companion object {
            const val android = "android"
        }
    }

    interface RoleType{
        companion object {
            const val customer = 3
            const val pro = 2
        }
    }

    interface Key{
        companion object{
            const val post = "post"
            const val id = "id"
            const val position = "position"
        }
    }

}

