package co.za.mtn.academy.itsgotime.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User (
    var id: String,
    var name: String,
    val roleName: String,
    val profileUrl: String
): Parcelable