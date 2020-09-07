package com.albuquerque.menuly.extensions

import android.view.View
import androidx.core.content.ContextCompat
import com.albuquerque.menuly.R
import com.google.android.material.snackbar.Snackbar

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setInisible() {
    this.visibility = View.INVISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.toggleVisibility() {
    val visible = this.visibility == View.VISIBLE

    if(visible)
        this.setGone()
    else
        this.setVisible()

}

fun View.showSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).setBackgroundTint(ContextCompat.getColor(this.context, R.color.colorAccent)).show()
}