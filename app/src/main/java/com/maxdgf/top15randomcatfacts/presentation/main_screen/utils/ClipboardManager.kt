package com.maxdgf.top15randomcatfacts.presentation.main_screen.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

class ClipboardManager(context: Context) {
    private val _clipboard =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager // get clipboard manager

    /** Sets text to clipboard.
     * @param text text string.
     * @param label clipboard item label. */
    fun setTextToClipboard(
        text: String,
        label: String = "Copied cat fact",
    ) {
        val clipData = ClipData.newPlainText(label, text) // new clip data
        _clipboard.setPrimaryClip(clipData) // set clip data
    }
}