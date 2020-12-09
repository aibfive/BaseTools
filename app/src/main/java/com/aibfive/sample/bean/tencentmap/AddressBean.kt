package com.aibfive.sample.bean.tencentmap

import com.aibfive.basetools.adapter.select.SelectItemEntity

class AddressBean() : SelectItemEntity {

    var title : String = ""
    var address : String = ""
    var latitude : Double = 0.0
    var longitude : Double = 0.0

    constructor(title : String, address : String, latitude : Double, longitude : Double) : this(){
        this.title = title
        this.address = address
        this.latitude = latitude
        this.longitude = longitude
    }

    override var select: Boolean
        get() = false
        set(value) {}

    override var tag: Any?
        get() = title
        set(value) {}
}