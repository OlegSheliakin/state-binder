package com.olegsheliakin.statebinder

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainState(
    val label: String,
    val errorText: String?
) : Parcelable, State