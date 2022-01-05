package com.cwi.matheus.pokeapp.util

import android.app.AlertDialog
import android.content.Context
import com.cwi.matheus.pokeapp.R

fun showConfirmDialog(
    context: Context,
    title : String,
    onConfirm : () -> Unit
) {

    val yesText = context.getString(R.string.txt_yes)
    val noText = context.getString(R.string.txt_no)
    AlertDialog.Builder(context)
        .setTitle(title)
        .setPositiveButton(yesText) { dialog, _ ->
            onConfirm()
            dialog.dismiss()
        }
        .setNegativeButton(noText) {
                dialog, _ ->
            dialog.cancel()
        }
        .show()
}