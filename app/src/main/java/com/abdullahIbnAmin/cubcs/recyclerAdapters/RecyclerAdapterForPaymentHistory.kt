package com.abdullahIbnAmin.cubcs.recyclerAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdullahIbnAmin.cubcs.R
import com.abdullahIbnAmin.cubcs.models.BlogItem
import com.abdullahIbnAmin.cubcs.models.PaymentHistoryItem
import kotlinx.android.synthetic.main.recycler_list_items_payment_history_details_layout.view.*

class RecyclerAdapterForPaymentHistory: RecyclerView.Adapter<RecyclerAdapterForPaymentHistory.MyViewHolder>(){

    var items = ArrayList<PaymentHistoryItem>()

    fun setUpdatedData(items: ArrayList<PaymentHistoryItem>){
        this.items = items
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_items_payment_history_details_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
//        holder.activity_name.text = currentItem.activity_name
//        holder.cardIssuer.text = currentItem.cardIssuer
//        holder.payment_amount.text = currentItem.amount
//        holder.tran_id.text = currentItem.payment_tran_id
//        holder.payment_status.text = currentItem.payment_status
//        holder.userEmail.text = currentItem.userEmail
//        holder.payment_date.text = currentItem.date
    }

    override fun getItemCount(): Int = items.size

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val userEmail = itemView.userEmail
        val activity_name = itemView.activity_name

        val payment_status = itemView.payment_status
        val payment_amount = itemView.payment_amount
        val cardIssuer = itemView.cardIssuer
        val tran_id = itemView.tran_id
        val payment_date = itemView.payment_date

        fun bind(data: PaymentHistoryItem){
            userEmail.text = "Email: ${data.userEmail}"
            activity_name.text = "Activity: ${data.activity_name}"
            payment_status.text = "Status: ${data.payment_status}"
            payment_amount.text = "Tk: ${data.amount}"
            cardIssuer.text = "Payment Method: ${data.cardIssuer}"
            tran_id.text = "Transection ID: ${data.payment_tran_id}"
            payment_date.text = "Date: ${data.date}"
        }
    }

}