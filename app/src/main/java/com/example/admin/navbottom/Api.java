package com.example.admin.navbottom;

import com.example.admin.navbottom.PaytmClasses.Checksum;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Belal on 1/10/2018.
 */

public interface Api {

    //this is the URL of the paytm folder that we added in the server
    //make sure you are using your ip else it will not work
    String BASE_URL = "http:///YOUR IP/paytm/";  //HERE YOUR IP ADDRESS KEEP IN MIND THAT YOUR  LOCAL SERVER and device should be in the same network

    @FormUrlEncoded
    @POST("generateChecksum.php")
    Call<Checksum> getChecksum(
            @Field("MID") String mId,
            @Field("ORDER_ID") String orderId,
            @Field("CUST_ID") String custId,
            @Field("CHANNEL_ID") String channelId,
            @Field("TXN_AMOUNT") String txnAmount,
            @Field("WEBSITE") String website,
            @Field("CALLBACK_URL") String callbackUrl,
            @Field("INDUSTRY_TYPE_ID") String industryTypeId
    );

}
