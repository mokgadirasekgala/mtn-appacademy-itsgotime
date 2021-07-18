package co.za.mtn.academy.itsgotime.core.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class User(
    var id: Int = 0, //added id
    @SerializedName("first_name") var name: String,
    @SerializedName("role") var roleName: String,
    @SerializedName("profile_pic") var profileUrl: String
) : Parcelable

data class ErrorResponse(val message: String?)