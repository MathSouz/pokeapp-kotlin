package com.cwi.matheus.pokeapp.extension

import android.view.View

fun View.visibleOrGone(visible : Boolean) {
    this.visibility = if(visible) View.VISIBLE else View.GONE
}