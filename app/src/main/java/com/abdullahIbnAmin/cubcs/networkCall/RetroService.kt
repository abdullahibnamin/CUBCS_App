package com.abdullahIbnAmin.cubcs.networkCall

import android.content.Context
import com.abdullahIbnAmin.cubcs.models.*
import com.auth0.android.jwt.JWT
import retrofit2.Call
import retrofit2.http.*


interface RetroService {

//    @GET("show_all_activities/Programming")
//    suspend fun getActivties(): RecyclerList

    @POST("device_info")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun sendDeviceInfo(@Body params: DeviceInfoModel): Call<DeviceInfoModel>

    @POST("app/signup")
    @Headers("Accept: application/json", "Content-Type: application/json")
    fun createUser(@Body params: SignUp): Call<SignUpResponse>

    @POST("app/login")
    @Headers("Accept: application/json", "Content-Type: application/json")
    fun loginUser(@Body params: login): Call<loginResponse>

    @POST("app/report")
    @Headers("Accept: application/json", "Content-Type: application/json")
    fun report(@Body params: Report): Call<ReportResponse>

    @GET("api/blog/all")
    suspend fun getAllBlogs(): Blog

    @GET("api/event/all")
    suspend fun getAllEvents(): Event

    @GET("api/bulletin/all")
    suspend fun getAllBulletin(): Bulletin

    @GET("api/gallery/all")
    suspend fun getAllImagesForGallery(): Gallery

    @GET("api/activities/Programming")
    suspend fun getAllActivities(): Activities

    @GET("api/payment/history/{email}")
    suspend fun getPaymentHistory(@Path("email") email: String): PaymentHistory
}















