package com.example.kisileruygulamasimvvm.data.entity

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Kisiler(var kisi_id:String? = "",
                   var kisi_ad:String? ="" ,
                   var kisi_tel:String? = "") : java.io.Serializable {



}