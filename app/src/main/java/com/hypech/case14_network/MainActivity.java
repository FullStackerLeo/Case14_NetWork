package com.hypech.case14_network;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String DEBUG_TAG = "NetworkStatusExample";
        String isWifi = isWifiConnected(this);
        int i = getConnectionType(this);

        Log.e(DEBUG_TAG, "isWifi: " + isWifi);
        Log.e(DEBUG_TAG, "1 2 3: " + i);
    }

    public static int getConnectionType(Context context) {
        int result = 0; // Returns connection type. 0: none; 1: mobile data; 2: wifi
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    result = 2;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    result = 1;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                    result = 3;
                }else{
                    result = 888;
                }
            }
        }
        return result;
    }

    public static String isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        String k="d";
        if (context != null) {
            Network network = cm.getActiveNetwork();
            if (network != null) {
                NetworkCapabilities nc = cm.getNetworkCapabilities(network);
                if (nc != null) {
                    if (nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {//WIFI
                        k =  "WIFI";
                    } else if (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {//移动数据
                        k =  "Cell";
                    }else{
                        k =  " no net";
                    }
                }
            }
        }
        return k;
    }
}
