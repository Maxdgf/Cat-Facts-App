package com.maxdgf.top15randomcatfacts.presentation.main_screen

sealed class CatFactsResult {
    data class Content(val list: List<String>) : CatFactsResult()
    data class Error(val message: String?) : CatFactsResult()
    object Initial : CatFactsResult()
}