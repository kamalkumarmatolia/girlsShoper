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
         productName : String,
         payableAmount : Int,
         userEmail : String,
         userName : String,
         userNumbar : String
     ) {

        val activity:Activity = this
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name","GirlsShoper")
            options.put("order",productName)
            //You can omit the image option to fetch the image from the Dashboard
            options.put("image","https://play-lh.googleusercontent.com/VmdjZwbYHdiNyWnbZbbSSq78y-XP0QTY-9uH7cI09ZKijLQJhQx3fnKWG9nhdWJj5g")
            options.put("theme.color", "#3399cc");
            options.put("currency","INR");
            options.put("amount",payableAmount*100)//pass amount in currency subunits

            val retryObj = JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("userName", userName)
            prefill.put("email",userEmail)
            prefill.put("contact",userNumbar)

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

