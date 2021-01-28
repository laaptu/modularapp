package org.ahivs.base.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

//just a placeholder Fragment for the entire application
//and enforcing to use AndroidX fragment in the entire application
abstract class BaseFragment(@LayoutRes contentLayoutId: Int = 0) : Fragment(contentLayoutId)