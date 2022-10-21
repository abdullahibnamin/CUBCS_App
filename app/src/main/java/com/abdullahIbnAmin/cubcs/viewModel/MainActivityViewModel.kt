package com.abdullahIbnAmin.cubcs.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdullahIbnAmin.cubcs.models.*
import com.abdullahIbnAmin.cubcs.networkCall.RetroInstance
import com.abdullahIbnAmin.cubcs.networkCall.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel(){
    var recyclerListLiveData: MutableLiveData<RecyclerList> = MutableLiveData()
    var recyclerListLiveDataForBlogs: MutableLiveData<Blog> = MutableLiveData()
    var recyclerListLiveDataForGallery: MutableLiveData<Gallery> = MutableLiveData()
    val recyclerListLiveDataForBulletin: MutableLiveData<Bulletin> = MutableLiveData()
    val recyclerListLiveDataForActivities: MutableLiveData<Activities> = MutableLiveData()
    val LiveDataForEvents: MutableLiveData<Event> = MutableLiveData()
    var sendDeviceInfo : MutableLiveData<DeviceInfoModel> = MutableLiveData()
    var sendPaymentInfoLiveData : MutableLiveData<PaymentInfo> = MutableLiveData()
    var createUserLiveData : MutableLiveData<SignUpResponse> = MutableLiveData()
    var loginUserLiveData : MutableLiveData<loginResponse> = MutableLiveData()
    var reportLiveData : MutableLiveData<ReportResponse> = MutableLiveData()
    var recyclerListLiveDataForPaymentHistory: MutableLiveData<PaymentHistory> = MutableLiveData()

    fun getRecyclerListObserver(): MutableLiveData<RecyclerList> {
        return recyclerListLiveData
    }
    fun getRecyclerListObserverForBlog(): MutableLiveData<Blog> {
        return recyclerListLiveDataForBlogs
    }
    fun getObserverForEvent(): MutableLiveData<Event> {
        return LiveDataForEvents
    }
    fun getRecyclerListObserverForGallery(): MutableLiveData<Gallery> {
        return recyclerListLiveDataForGallery
    }
    fun getRecyclerListObserverForPaymentHistory(): MutableLiveData<PaymentHistory> {
        return recyclerListLiveDataForPaymentHistory
    }
    fun getRecyclerListObserverForBulletin(): MutableLiveData<Bulletin>{
        return recyclerListLiveDataForBulletin
    }
    fun getRecyclerListObserverForActivities(): MutableLiveData<Activities>{
        return recyclerListLiveDataForActivities
    }
    fun getSendDeviceInfoObserver():MutableLiveData<DeviceInfoModel>{
        return sendDeviceInfo
    }

    fun getSendPaymentInfoObserver():MutableLiveData<PaymentInfo>{
        return sendPaymentInfoLiveData
    }
    fun getCreateNewUserObserver():MutableLiveData<SignUpResponse>{
        return createUserLiveData
    }

    fun getLoginUserObserver():MutableLiveData<loginResponse>{
        return loginUserLiveData
    }
    fun getReportObserver():MutableLiveData<ReportResponse>{
        return reportLiveData
    }

//    fun makeApiCall() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java).getActivties()
//            recyclerListLiveData.postValue(retroInstance)
//        }
//    }

    fun apiCallForBlogs(){
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java).getAllBlogs()
            recyclerListLiveDataForBlogs.postValue(retroInstance)
        }
    }

    fun apiCallForEvents(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java).getAllEvents()
                LiveDataForEvents.postValue(retroInstance)
            } catch (e: Exception){
//                Log.d("testt", "${e.printStackTrace()} + timeOut")
            }
        }
    }

    fun apiCallForPaymentHistory(email: String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java).getPaymentHistory(email)
                recyclerListLiveDataForPaymentHistory.postValue(retroInstance)
            } catch (e: Exception){
//                Log.d("testt", "${e.printStackTrace()} + timeOut")
            }
        }
    }

    fun apiCallForGallery(){
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java).getAllImagesForGallery()
            recyclerListLiveDataForGallery.postValue(retroInstance)
        }
    }

    fun apiCallForBulletin(){
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java).getAllBulletin()
            recyclerListLiveDataForBulletin.postValue(retroInstance)
        }
    }

    fun apiCallForActivities(){
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java).getAllActivities()
            recyclerListLiveDataForActivities.postValue(retroInstance)
        }
    }

    // https://www.youtube.com/watch?v=urZsKWxIj78
    fun apiCallForCreateUser(signup: SignUp){
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.createUser(signup)

        call.enqueue(object : Callback<SignUpResponse> {
            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                createUserLiveData.postValue(null)
            }
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                if(response.isSuccessful){
                    createUserLiveData.postValue(response.body())
                    Log.d("test", "${response.body()}")
                }
                else{
                    createUserLiveData.postValue(null)
                }
            }
        })
    }

    fun apiCallForUserLogin(login: login){
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.loginUser(login)

        call.enqueue(object : Callback<loginResponse> {
            override fun onFailure(call: Call<loginResponse>, t: Throwable) {
                loginUserLiveData.postValue(null)
            }
            override fun onResponse(call: Call<loginResponse>, response: Response<loginResponse>) {
                if(response.isSuccessful){
                    loginUserLiveData.postValue(response.body())
//                    Log.d("test", "${response.body()}")
                }
                else{
                    createUserLiveData.postValue(null)
                }
            }
        })
    }


    fun apiCallForUserReport(report: Report){
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.report(report)

        call.enqueue(object : Callback<ReportResponse> {
            override fun onFailure(call: Call<ReportResponse>, t: Throwable) {
                reportLiveData.postValue(null)
            }
            override fun onResponse(call: Call<ReportResponse>, response: Response<ReportResponse>) {
                if(response.isSuccessful){
                    reportLiveData.postValue(response.body())
//                    Log.d("test", "${response.body()}")
                }
                else{
                    reportLiveData.postValue(null)
                }
            }
        })
    }


//    fun apiCallForDeviceInfo(dInfo: DeviceInfoModel){
//        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
//        val call = retroInstance.sendDeviceInfo(dInfo)
//        call.enqueue(object: Callback<DeviceInfoModel>{
//            override fun onResponse(call: Call<DeviceInfoModel>, response: Response<DeviceInfoModel>) {
//                if(response.isSuccessful){
//                    sendDeviceInfo.postValue(response.body())
//                }
//                else{
//                    sendDeviceInfo.postValue(null)
//                }
//            }
//
//            override fun onFailure(call: Call<DeviceInfoModel>, t: Throwable) {
//                sendDeviceInfo.postValue(null)
//            }
//        })
//    }


}