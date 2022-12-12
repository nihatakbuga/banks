package com.enqura.banks.data.remote.response

import com.google.gson.annotations.SerializedName

data class BankResponseItem(
    @SerializedName("ID")
    val ID: Int,
    @SerializedName("dc_ADRES")
    val dc_ADRES: String,
    @SerializedName("dc_ADRES_ADI")
    val dc_ADRES_ADI: String,
    @SerializedName("dc_BANKA_SUBE")
    val dc_BANKA_SUBE: String,
    @SerializedName("dc_BANKA_TIPI")
    val dc_BANKA_TIPI: String,
    @SerializedName("dc_BANK_KODU")
    val dc_BANK_KODU: String,
    @SerializedName("dc_BOLGE_KOORDINATORLUGU")
    val dc_BOLGE_KOORDINATORLUGU: String,
    @SerializedName("dc_EN_YAKIM_ATM")
    val dc_EN_YAKIM_ATM: String,
    @SerializedName("dc_ILCE")
    val dc_ILCE: String,
    @SerializedName("dc_ON_OFF_LINE")
    val dc_ON_OFF_LINE: String,
    @SerializedName("dc_ON_OFF_SITE")
    val dc_ON_OFF_SITE: String,
    @SerializedName("dc_POSTA_KODU")
    val dc_POSTA_KODU: String,
    @SerializedName("dc_SEHIR")
    val dc_SEHIR: String
)