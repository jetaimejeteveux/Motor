package com.erpeel.motor.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MotorTerjual (
    var harga: String ?="",
    var jenis: String ?="",
    var kapasitasMesin: String ?="",
    var plat: String ?="",
    var tahun: String ?="",
    var url: String ?="",
    var model: String ?="",
    var tahunTerjual: String? =""
): Parcelable