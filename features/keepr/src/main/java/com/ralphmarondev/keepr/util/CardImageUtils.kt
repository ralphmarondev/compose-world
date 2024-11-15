package com.ralphmarondev.keepr.util

import com.ralphmarondev.keepr.R

fun getCardImage(name: String): Int {
    return when (name.lowercase().replace(" ", "")) {
        "social" -> R.drawable.social
        "development" -> R.drawable.development
        "gaming" -> R.drawable.gaming
        "entertainment" -> R.drawable.entertainment
        "tiktok" -> R.drawable.tiktok
        "youtube" -> R.drawable.youtube
        "instagram" -> R.drawable.instagram
        "facebook" -> R.drawable.facebook
        "linkedin" -> R.drawable.linkedin
        else -> R.drawable.android
    }
}