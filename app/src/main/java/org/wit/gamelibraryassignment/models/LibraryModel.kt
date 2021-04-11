package org.wit.gamelibraryassignment.models


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LibraryModel(var id: Long = 0,
                          var title: String = "",
                          var description: String = "") : Parcelable