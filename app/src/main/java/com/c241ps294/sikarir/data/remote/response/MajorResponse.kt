package com.c241ps294.sikarir.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Parcelize
data class MajorResponse(

	@field:SerializedName("totalMajors")
	val totalMajors: Int? = null,

	@field:SerializedName("listMajor")
	val listMajor: List<ListMajorItem?>? = null,

	@field:SerializedName("totalPages")
	val totalPages: Int? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("currentPage")
	val currentPage: Int? = null
) : Parcelable

@Entity(tableName = "major")
@Parcelize
data class ListMajorItem(

	@field:SerializedName("photoUrl")
	val photoUrl: String? = null,

	@field:SerializedName("listUniversitasTerkait")
	val listUniversitasTerkait: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	@PrimaryKey
	val id: String
) : Parcelable
