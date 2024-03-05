package com.ambiws.numbers_ivtest.base.list

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DefaultListDiffer<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
}
