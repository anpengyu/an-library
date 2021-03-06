package com.apy.library.net.ktExtends

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

inline fun <reified T> Context.createIntent() = Intent(this, T::class.java)

inline fun <reified T : Activity> Context.startActivity() = startActivity(Intent(this, T::class.java))

inline fun <reified T : ViewModel> FragmentActivity.getViewModel() = ViewModelProviders.of(this).get(T::class.java)

inline fun <reified T : ViewModel> Fragment.getViewModel() = ViewModelProviders.of(this).get(T::class.java)

inline fun <reified T : Activity> Fragment.startActivity() = activity?.run { startActivity(Intent(this, T::class.java)) }


// 显示一个fragment
fun FragmentActivity.showFragment(fragment: Fragment, hiddens: Array<Fragment>) {
    with(supportFragmentManager) {
        val transaction = beginTransaction()
        for (hidden in hiddens) {
            transaction.hide(hidden)
        }
        transaction.show(fragment)
        transaction.commitAllowingStateLoss()
    }
}

// 把一个fragment数组添加到页面中
fun FragmentActivity.addFragment(@IdRes containerViewId: Int, fragments: Array<Fragment>) {
    with(supportFragmentManager) {
        val transaction = beginTransaction()
        for (fragment in fragments) {
            transaction.add(containerViewId, fragment)
        }
        transaction.commitAllowingStateLoss()
    }
}

inline fun <reified T : FragmentActivity> Fragment.activity(runnable: T.() -> Unit) {
    activity?.let {
        it as T
        it.runnable()
    }
}

fun Context.dp2px(dps: Int) = Math.round(resources.displayMetrics.density * dps)

fun Context.px2dp(pxs: Int) = Math.round(pxs / resources.displayMetrics.density)

fun Fragment.dp2px(dps: Int) = activity?.dp2px(dps) ?: 0

fun Fragment.px2dp(pxs: Int) = activity?.px2dp(pxs) ?: 0

