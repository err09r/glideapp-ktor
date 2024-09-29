@file:Suppress("LocalVariableName", "MemberVisibilityCanBePrivate")

package com.apsl.glideapp.api.utils

class PaginationData(page: String?, limit: String?) {

    val limit: Int?
    val offset: Long

    init {
        val _page = page?.toIntOrNull() ?: DEFAULT_PAGE
        this.limit = limit?.toIntOrNull()
        offset = (_page.minus(1)).times(limit?.toLong() ?: 0)
    }

    operator fun component1() = limit
    operator fun component2() = offset

    private companion object {
        private const val DEFAULT_PAGE = 1
    }
}
