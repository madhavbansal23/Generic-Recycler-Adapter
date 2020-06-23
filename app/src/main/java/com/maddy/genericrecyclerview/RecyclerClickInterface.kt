package com.maddy.genericrecyclerview

import android.view.View

interface RecyclerClickInterface<T> {
    fun onClick(v: View?, model: T, pos: Int)
}
