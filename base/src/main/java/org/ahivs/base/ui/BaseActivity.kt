package org.ahivs.base.ui

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

//just a placeholder Activity for the entire application
//and enforcing all the Activity to use AppcompatActivity
abstract class BaseActivity(@LayoutRes contentLayoutId: Int = 0) :
    AppCompatActivity(contentLayoutId)