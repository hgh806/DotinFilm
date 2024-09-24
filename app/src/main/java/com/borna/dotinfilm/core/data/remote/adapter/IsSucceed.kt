package com.borna.dotinfilm.core.data.remote.adapter

data class IsSucceed<T>(
    val data: T? = null,
    val code: Int = 200,
    val isError: Boolean = false,
    val error: String? = null,
)
