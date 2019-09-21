package com.gturedi.marketim

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


// created by @gturedi at 9/22/19
class OrdersAdpater(val items: List<OrderModel>) :
    RecyclerView.Adapter<OrdersAdpater.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    class CustomViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val lnrItem: TextView = view.findViewById(R.id.lnrItem)
        val rlvDetail: TextView = view.findViewById(R.id.rlvDetail)
        val tvMonthNo: TextView = view.findViewById(R.id.tvMonthNo)
        val tvMonthName: TextView = view.findViewById(R.id.tvMonthName)
        val tvMarketName: TextView = view.findViewById(R.id.tvMarketName)
        val tvOrderName: TextView = view.findViewById(R.id.tvOrderName)
        val tvState: TextView = view.findViewById(R.id.tvState)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val tvOrderDetail: TextView = view.findViewById(R.id.tvOrderDetail)
        val tvSummaryPrice: TextView = view.findViewById(R.id.tvSummaryPrice)

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
                if (rlvDetail.visibility == VISIBLE) {
                    rlvDetail.visibility = GONE
                }  else {
                    rlvDetail.visibility = VISIBLE
                }
            }
        }

    }

}