package com.abdullahIbnAmin.cubcs

import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abdullahIbnAmin.cubcs.models.DeviceInfoModel
import com.abdullahIbnAmin.cubcs.viewModel.MainActivityViewModel

class Splash_screen : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        "https://www.youtube.com/watch?v=iD8v02n6HGo"
        val wifiM = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
        val deviceIP = wifiM.connectionInfo.ipAddress.toString()
        val mac = wifiM.connectionInfo.macAddress.toString()
//
//        Log.i("TAG","APP VERSION CODE: " + BuildConfig.VERSION_CODE)
//        Log.i("TAG","APP VERSION NAME: " + BuildConfig.VERSION_NAME)
//        Log.i("TAG","MODEL: " + Build.MODEL)
//        Log.i("TAG","ID: " + Build.ID)
//        Log.i("TAG","Manufacture: " + Build.MANUFACTURER)
//        Log.i("TAG","type: " + Build.TYPE)
//        Log.i("TAG","user: " + Build.USER)
//        Log.i("TAG","BASE: " + Build.VERSION_CODES.BASE)
//        Log.i("TAG","INCREMENTAL: " + Build.VERSION.INCREMENTAL)
//        Log.i("TAG","SDK INT: " + Build.VERSION.SDK_INT)
//        Log.i("TAG","BOARD: " + Build.BOARD)
//        Log.i("TAG","BRAND: " + Build.BRAND)
//        Log.i("TAG","HOST: " + Build.HOST)
//        Log.i("TAG","FINGERPRINT: "+ Build.FINGERPRINT)
//        Log.i("TAG","Version RELEASE: " + Build.VERSION.RELEASE)
//        Log.i("TAG","getRadioVersion: " + Build.getRadioVersion())
//        Log.i("TAG","HARDWARE: " + Build.HARDWARE)
//        Log.i("TAG","BOOTLOADER: " + Build.BOOTLOADER)
//        Log.i("TAG","TIME: " + Build.TIME)
//        Log.i("TAG","DISPLAY: " + Build.DISPLAY)
//        Log.i("TAG","PRODUCT: " + Build.PRODUCT)
//        Log.i("TAG","DEVICE: " + Build.DEVICE)
//        Log.i("TAG","CODENAME: " + Build.VERSION.CODENAME)
//
//        Log.i("TAG","Device IP: $deviceIP")
//        Log.i("TAG","MAC: $mac")

//        Log.i("TAG","SOC MANUFACTURER: " + Build.SOC_MANUFACTURER)
//        Log.i("TAG","SOC MODEL: " + Build.SOC_MODEL)
//        Log.i("TAG","ODM_SKU: " + Build.ODM_SKU)
//        Log.i("TAG","SKU: " + Build.SKU)

        initViewModel()
        deviceInfo(deviceIP, mac)

        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }

    private fun deviceInfo(deviceIP:String, mac:String){
        val info = DeviceInfoModel(
            BuildConfig.VERSION_CODE.toString(),
            BuildConfig.VERSION_NAME,
            Build.MODEL,
            Build.ID,
            Build.MANUFACTURER,
            Build.TYPE,
            Build.USER,
            Build.VERSION_CODES.BASE.toString(),
            Build.VERSION.INCREMENTAL,
            Build.VERSION.SDK_INT.toString(),
            Build.BOARD,
            Build.BRAND,
            Build.HOST,
            Build.FINGERPRINT,
            Build.VERSION.RELEASE,
            Build.getRadioVersion().toString(),
            Build.HARDWARE,
            Build.BOOTLOADER,
            Build.TIME.toString(),
            Build.DISPLAY,
            Build.PRODUCT,
            Build.DEVICE,
            Build.VERSION.CODENAME,
            deviceIP,
            mac
        )

        viewModel.apiCallForDeviceInfo(info)
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getSendDeviceInfoObserver().observe(this, Observer<DeviceInfoModel> {
            if (it == null){
                Log.d("initViewModel", "failed")
            }
            else{
                Log.d("initViewModel", "failed")
            }
        })
    }
}