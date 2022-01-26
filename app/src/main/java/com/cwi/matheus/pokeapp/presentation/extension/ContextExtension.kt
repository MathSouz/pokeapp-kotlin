package com.cwi.matheus.pokeapp.presentation.extension

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import com.cwi.matheus.pokeapp.R

fun Context.showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun Context.showConfirmDialog(
    title: String,
    onConfirm: () -> Unit
) {
    val yesText = this.getString(R.string.txt_yes)
    val noText = this.getString(R.string.txt_no)
    AlertDialog.Builder(this)
        .setTitle(title)
        .setPositiveButton(yesText) { dialog, _ ->
            onConfirm()
            dialog.dismiss()
        }
        .setNegativeButton(noText) { dialog, _ ->
            dialog.cancel()
        }
        .show()
}