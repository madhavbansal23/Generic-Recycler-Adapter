package com.maddy.genericrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

open class GenericRecyclerAdapter<T, LVM : ViewDataBinding>(
    items: ArrayList<T>,
    private var layoutId: Int,
    private var bindingInterface: GenericRecyclerInterface<LVM, T>
) : RecyclerView.Adapter<GenericRecyclerAdapter<T, LVM>.RecyclerViewHolder>() {

    private var items: ArrayList<T> = items

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var binding: LVM = DataBindingUtil.bind<LVM>(view)!!

        fun bindData(model: T, pos: Int) {
            bindingInterface.bindData(binding, model, pos)
            binding.executePendingBindings()
        }
    }

    fun updateList(newList: ArrayList<T>) {
        DiffUtil.calculateDiff(MyDiffCallback(this.items, newList)).dispatchUpdatesTo(this)
        items = newList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)

        val viewHolder: RecyclerViewHolder
        viewHolder = RecyclerViewHolder(v)
        return viewHolder
    }


    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = items[position]
        holder.bindData(item, position)
    }


    override fun getItemCount(): Int {
        return items.size
    }
}