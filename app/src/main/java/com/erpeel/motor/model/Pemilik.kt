package com.erpeel.motor.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pemilik (
    var alamat: String ?="",
    var nama: String ?=""
): Parcelable