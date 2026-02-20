package com.example.girlsshoper

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.girlsshoper.presentation.components.card.shipingProductDetailCard
import com.example.girlsshoper.presentation.navigation.navApp
import com.example.girlsshoper.presentation.screens.shipingScreenUi
import com.example.girlsshoper.ui.theme.GirlsShoperTheme
import com.example.girlsshoper.ui.theme.MainColor
import com.google.firebase.auth.FirebaseAuth
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import javax.inject.Inject
import kotlin.toString

@AndroidEntryPoint
class MainActivity : ComponentActivity(), PaymentResultListener {
    @Inject
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                navApp(firebaseAuth = firebaseAuth)
//                shipingScreenUi()
            }
        }
        Checkout.preload(applicationContext)
        val co = Checkout()
        // apart from setting it in AndroidManifest.xml, keyId can also be set
        // programmatically during runtime
        co.setKeyID(R.string.rzr_key.toString())
    }

    fun startPayment(
        name : String,
        payableAmount : Int
    ) {
        /*
        *  You need to pass the current activity to let Razorpay create CheckoutActivity
        * */
        val activity: Activity = this
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name",name)
            options.put("description","Demoing Charges")
            //You can omit the image option to fetch the image from the Dashboard
            options.put("image","https://images.search.yahoo.com/images/view;_ylt=Awr48ncrzU1nHPs3OFqJzbkF;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzQ2MzBkNzA0MTFmNTE2MThkYjY5Y2QyMWI2ZDMxYWVhBGdwb3MDMTMEaXQDYmluZw--?back=https%3A%2F%2Fimages.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Dindia%2Bekart%2Blogo%26type%3DE210US885G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D13&w=512&h=512&imgurl=play-lh.googleusercontent.com%2FVmdjZwbYHdiNyWnbZbbSSq78y-XP0QTY-9uH7cI09ZKijLQJhQx3fnKWG9nhdWJj5g&rurl=https%3A%2F%2Fplay.google.com%2Fstore%2Fapps%2Fdetails%3Fid%3Dcom.apextiming.ekart&size=63KB&p=india+ekart+logo&oid=4630d70411f51618db69cd21b6d31aea&fr2=piv-web&fr=mcafee&tt=eKart+-+Apps+on+Google+Play&b=0&ni=21&no=13&ts=&tab=organic&sigr=jzIZLWtPlQmM&sigb=GnlZx6Gr_nfC&sigi=MvcjfedGHhK.&sigt=khZBK.bOUbYx&.crumb=uY3/0xJzLvg&fr=mcafee&fr2=piv-web&type=E210US885G0")
            options.put("theme.color", MainColor);
            options.put("currency","INR");
            options.put("amount",payableAmount)//pass amount in currency subunits

            val retryObj = JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email","aman.nittc@gmail.com")
            prefill.put("contact","8294888127")

            options.put("prefill",prefill)
            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        TODO("Not yet implemented")
    }
}

