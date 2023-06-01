package com.ltdd.orderfood.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import com.ltdd.orderfood.Model.Request;
import com.ltdd.orderfood.Model.User;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Common {
    public static User currentUser;
    public static Request currentRequest;

    public static String convertCodeToStatus(String status) {
        if (status.equals("0"))
            return "Đã đặt hàng";
        else if (status.equals("1"))
            return "Đang giao";
        else
            return "Giao thành công";


    }

    public static String getDate() {
        long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeMillis);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String formattedDateTime = formatter.format(date);
        return formattedDateTime;
    }

    public static boolean isConnectedToInterner(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;
    }

    public static final String DELETE = "Delete";
    public static final String USER_KEY = "User";
    public static final String PDW_KEY = "Password";

}
