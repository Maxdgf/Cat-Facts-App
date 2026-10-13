package com.maxdgf.top15randomcatfacts.presentation.main_screen.utils

import android.content.Context
import android.content.Intent

class ShareManager(private val context: Context) {
    fun shareMessage(message: String): Boolean {
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }
        val intentChooser = Intent.createChooser(intent, null)

        return if (intentChooser.resolveActivity(context.packageManager) != null) {
            context.startActivity(intentChooser)
            true
        } else false
    }
}
