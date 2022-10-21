package com.abdullahIbnAmin.cubcs.models

class PaymentHistory: ArrayList<PaymentHistoryItem>()

data class PaymentHistoryItem(
    val pid: String,
    val amount: String,
    val cardIssuer: String,
    val activity_name: String,
    val userEmail: String,
    val payment_status: String,
    val payment_tran_id: String,
    val date: String
)