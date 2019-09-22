package com.gturedi.marketim

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_order.*

// created by @gturedi at 9/22/19
class OrdersAdapter(val items: List<OrderModel>) :
    RecyclerView.Adapter<OrdersAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CustomViewHolder(parent.inflate(R.layout.item_order))

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    class CustomViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: OrderModel) {
            tvMonthNo.text = item.date.toString()
            tvMonthName.text = item.month.toString()
            tvMarketName.text = item.marketName
            tvOrderName.text = item.orderName
            tvState.text = item.productState
            tvPrice.text = item.productPrice.toString()
            tvOrderDetail.text = item.productDetail.orderDetail
            tvSummaryPrice.text = item.productDetail.summaryPrice.toString()

            lnrItem.setOnClickListener {
                if (rlvDetail.isVisible) {
                    rlvDetail.hide()
                }  else {
                    rlvDetail.show()
                }
            }
        }

    }

}