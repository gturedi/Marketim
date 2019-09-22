package com.gturedi.marketim.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.gturedi.marketim.R
import com.gturedi.marketim.service.OrderModel
import com.gturedi.marketim.util.color
import com.gturedi.marketim.util.hide
import com.gturedi.marketim.util.inflate
import com.gturedi.marketim.util.show
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_order.*
import java.text.DateFormatSymbols

// created by @gturedi at 9/22/19
class OrdersAdapter(val items: List<OrderModel>) :
    RecyclerView.Adapter<OrdersAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CustomViewHolder(parent.inflate(R.layout.item_order))

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    class CustomViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: OrderModel) {
            tvDayNo.text = item.date.toString().padStart(2, '0')
            tvMonthName.text = DateFormatSymbols().months[item.month-1]
            tvMarketName.text = item.marketName
            tvOrderName.text = item.orderName
            tvState.text = item.productState.text
            tvState.setTextColor(tvState.color(item.productState.getColor()))
            vwState.setBackgroundColor(tvState.color(item.productState.getColor()))
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