package com.developer.u_glow.model.request

data class PostGlowRequest(
    var userId: Int?=null,
    var amount: String?=null,
    var numberOfPeople: Int?=null,
    var date: String?=null,
    var time: String?=null,
    var isGroup: Boolean?=null,
    var alternativeDate: String?=null,
    var alternativeTime: String?=null,
    var otherService: String?=null,
    var detail: String?=null,
    var address: String?=null,
    var latitude: Double?=null,
    var longitude: Double?=null,
    var isFlexible:Boolean?=false,
    var services: List<Int>?=null,
    var imageUrl:List<String>?=null,
)