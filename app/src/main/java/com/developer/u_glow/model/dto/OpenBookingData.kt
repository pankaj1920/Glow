package com.developer.u_glow.model.dto

data class OpenBookingData(
    var id:Int?=null,
    var amount:Int?=null,
    var status:Int?=null,
    var date:String?=null,
    var time:String?=null,
    var isFlexible:Boolean?=false,
    var address:String?=null,
    var services:List<OpenServiceData>?=null,
    var offerCount:Int?=null

)
