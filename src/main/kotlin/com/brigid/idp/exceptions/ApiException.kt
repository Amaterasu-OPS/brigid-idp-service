package com.brigid.idp.exceptions

open class ApiException(
    override val message: String,
    open val status: Int = 500
) : Exception(message)