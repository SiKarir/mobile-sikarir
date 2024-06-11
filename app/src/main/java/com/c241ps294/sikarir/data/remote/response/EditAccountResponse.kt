package com.c241ps294.sikarir.data.remote.response

import com.google.gson.annotations.SerializedName

data class EditAccountResponse(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
