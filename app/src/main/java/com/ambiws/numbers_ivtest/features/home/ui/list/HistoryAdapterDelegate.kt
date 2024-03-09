package com.ambiws.numbers_ivtest.features.home.ui.list

import com.ambiws.numbers_ivtest.R
import com.ambiws.numbers_ivtest.databinding.ItemHistoryBinding
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import java.text.SimpleDateFormat

object HistoryAdapterDelegate {

    fun historyAdapterDelegate(
        onItemClick: (HistoryItemModel) -> Unit,
        onUpdate: () -> Unit,
    ): AdapterDelegate<List<HistoryListItemModel>> {
        return adapterDelegateViewBinding<HistoryItemModel, HistoryListItemModel, ItemHistoryBinding>(
            { layoutInflater, parent ->
                ItemHistoryBinding.inflate(layoutInflater, parent, false)
            }
        ) {
            bind {
                with(binding) {
                    val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm")
                    tvDate.text = sdf.format(item.timestamp)
                    tvTitle.text = getString(R.string.fact_about, item.number)
                    binding.root.setOnClickListener {
                        onItemClick.invoke(item)
                    }
                    onUpdate.invoke()
                }
            }
        }
    }
}
