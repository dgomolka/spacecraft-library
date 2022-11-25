package com.psycodeinteractive.spacecraftlibrary.presentation.paging

import androidx.paging.AsyncPagingDataDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import kotlinx.coroutines.Dispatchers

object PagingTestUtil {
    fun <Type : Any> createPagingDiffer() = AsyncPagingDataDiffer(
        diffCallback = MyDiffCallback<Type>(),
        updateCallback = NoopListCallback(),
        workerDispatcher = Dispatchers.Main
    )
}

class NoopListCallback : ListUpdateCallback {
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
}

class MyDiffCallback<Type : Any> : DiffUtil.ItemCallback<Type>() {
    override fun areItemsTheSame(oldItem: Type, newItem: Type) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Type, newItem: Type) = oldItem == newItem
}
