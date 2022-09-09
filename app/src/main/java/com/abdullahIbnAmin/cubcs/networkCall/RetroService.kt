package com.abdullahIbnAmin.cubcs.networkCall

import com.abdullahIbnAmin.cubcs.models.*
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


    @GET("api/blog/all")
    suspend fun getAllBlogs(): Blog

    @GET("api/bulletin/all")
    suspend fun getAllBulletin(): Bulletin

    @GET("api/gallery/all")
    suspend fun getAllImagesForGallery(): Gallery

    @GET("api/activities/Programming")
    suspend fun getAllActivities(): Activities
}















