package com.erpeel.motor.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MotorTersedia (
    var harga: String ?="",
    var jenis: String ?="",
    var kapasitas_mesin: String ?="",
    var model: String ?="",
    var plat: String ?="",
    var tahun: String ?="",
    var url: String ?=""

): Parcelable