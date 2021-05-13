package com.aibfive.basetools.mvvm

import androidx.lifecycle.ViewModel
import com.aibfive.basetools.BR

abstract class BaseViewModel : ViewModel() {

    val id = BR.view_model

}