package com.c241ps294.sikarir.data.remote.response

import com.google.gson.annotations.SerializedName

data class EditAccountResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("user")
	val user: User
)

data class User(
	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("photoUrl")
	val photoUrl: String,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("isTakenQuiz")
	val isTakenQuiz: Boolean,
)
