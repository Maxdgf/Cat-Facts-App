package com.maxdgf.top15randomcatfacts.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CatFacts(
    val data: List<String>
)