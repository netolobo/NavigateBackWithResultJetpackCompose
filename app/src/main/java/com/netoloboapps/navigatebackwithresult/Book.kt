package com.netoloboapps.navigatebackwithresult

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val id: Int = 0,
    val title: String = "",
    val cover: String = "coverUrl"
) : Parcelable
