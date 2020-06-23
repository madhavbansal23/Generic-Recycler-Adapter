package com.maddy.genericrecyclerview

interface GenericRecyclerInterface<LVM, T>{
    fun bindData(binder: LVM, model: T, pos: Int)
}