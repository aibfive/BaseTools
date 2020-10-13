package com.aibfive.sample.ui.adapter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.aibfive.basetools.adapter.select.SelectItemEntity
import com.aibfive.sample.R

class SingleSelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_select)
        var select = SelectItemEntity(true, "1")
        var hashMap = HashMap<String, String>()
        hashMap.iterator()
    }
}