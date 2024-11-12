package com.example.librarymanagementsystem.extension

import android.view.View

fun View.setSafeOnClickListener(action: () -> Unit) {
    this.setOnClickListener {
        action()
        this.isClickable = false
        this.postDelayed({
            this.isClickable = true
        },2000)
    }
}