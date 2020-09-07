package com.albuquerque.menuly.widget

import android.app.Activity
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import com.albuquerque.menuly.R
import com.albuquerque.menuly.extensions.setVisible
import kotlinx.android.synthetic.main.layout_bottom_dialog.*

class BottomDialog {

    companion object {

        fun build(context: Activity): AlertDialog {

            val alertDialog = AlertDialog
                .Builder(context, R.style.BottomDialogStyle)
                .setView(R.layout.layout_bottom_dialog)
                .create()

            val layoutParams = (alertDialog.container?.layoutParams as? RelativeLayout.LayoutParams)?.apply {
                addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
            }

            alertDialog.container?.layoutParams = layoutParams

            alertDialog.show()
            return alertDialog

        }
    }

}

fun AlertDialog.title(title: String): AlertDialog {
    this.title.text = title.trim()
    this.title.setVisible()
    return this
}

fun AlertDialog.body(body: String): AlertDialog {
    this.subHeading.text = body.trim()
    this.subHeading.setVisible()
    return this
}

fun AlertDialog.icon(icon: Int): AlertDialog {
    this.image.apply {
        this.setImageResource(icon)
        this.setVisible()
    }
    return this
}

fun AlertDialog.onPositive(text: String, action: (() -> Unit)? = null): AlertDialog {
    this.positiveButton.apply {
        this.setVisible()
        this.text = text.trim()
        this.setOnClickListener {
            action?.invoke()
            dismiss()
        }
    }

    return this
}

fun AlertDialog.onNegative(text: String, action: (() -> Unit)? = null): AlertDialog {
    this.negativeButton.apply {
        this.setVisible()
        this.text = text.trim()
        this.setOnClickListener {
            action?.invoke()
            dismiss()
        }
    }
    return this
}