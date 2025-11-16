package com.brigid.idp.exceptions

data class DatabaseApiException(
    override  val message: String,
    override val status: Int = 500
) : ApiException(message, status)
