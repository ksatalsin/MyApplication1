package android1.myapplication1.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide


inline fun <reified T : Activity> Context.getIntent() = Intent(this, T::class.java)

inline fun FragmentActivity.setContentFragment(containerViewId: Int, f: () -> Fragment): Fragment? {
    val manager = supportFragmentManager
    val fragment = manager?.findFragmentById(containerViewId)
    fragment?.let { return it }
    return f().apply { manager?.beginTransaction()?.add(containerViewId, this)?.commit() }
}

fun ImageView.loadImage(photoUrl: String) {
    Glide.with(context)
            .load(photoUrl)
            .into(this)
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun View.showSnackbar(text: String) {
    Snackbar.make(this, text, Snackbar.LENGTH_LONG).show()
}

fun <T : Parcelable> Activity.extra(key: String, default: T? = null): Lazy<T> = lazy { intent?.extras?.getParcelable<T>(key) ?: default ?: throw Error("No value $key in extras") }
