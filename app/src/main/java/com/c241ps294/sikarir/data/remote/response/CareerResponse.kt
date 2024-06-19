package com.c241ps294.sikarir.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Parcelize
data class CareerResponse(

	@field:SerializedName("listCareer")
	val listCareer: List<ListCareerItem>,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Entity(tableName = "career")
@Parcelize
data class ListCareerItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("photoUrl")
	val photoUrl: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("listJurusanTerkait")
	val listJurusanTerkait: List<ListMajorItem?>? = null,

	@field:SerializedName("id")
	@PrimaryKey
	val id: String
) : Parcelable
