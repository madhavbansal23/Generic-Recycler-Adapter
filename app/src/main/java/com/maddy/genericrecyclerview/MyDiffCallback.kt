package com.maddy.genericrecyclerview

import androidx.recyclerview.widget.DiffUtil

class MyDiffCallback<T>(
    private var oldArticleList: ArrayList<T>,
    private var newArticleList: ArrayList<T>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldArticleList.get(oldItemPosition) == newArticleList.get(newItemPosition)
    }

    override fun getOldListSize(): Int {
        return oldArticleList.size
    }

    override fun getNewListSize(): Int {
        return newArticleList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldArticleList.get(oldItemPosition) == newArticleList.get(newItemPosition)
    }
}