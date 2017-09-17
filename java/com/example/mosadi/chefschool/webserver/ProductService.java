package com.example.mosadi.chefschool.webserver;

/**
 * Created by Mosadi on 2017/09/16.
 */

        import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.client.methods.HttpGet;

import java.util.ArrayList;

public class ProductService {
    private static final String TAG = "ProductService";

    private static final String PRODUCT_WEB_SERVICE_URL = "GET /Nontlantla%20Felani/students/ HTTP/1.1";

    public ArrayList<ProductService> getProducts() {
        Log.i(TAG, "getProducts ......");
        ArrayList<ProductService> productList = null;
        HttpGet httpGet = new HttpGet(PRODUCT_WEB_SERVICE_URL);

        //setting header to request for a JSON response
        httpGet.setHeader("Accept", "application/json");
        AndroidNetworkUtility httpUtil = new AndroidNetworkUtility();
        String productJSONStr = httpUtil.getHttpResponse(httpGet);
        Log.d(TAG, "Response: " + productJSONStr);
        productList = convertJson(productJSONStr);
        return productList;
    }

    private ArrayList<ProductService> convertJson(String productJSONStr) {
        ArrayList<ProductService> productList = null;
        if (productJSONStr != null && productJSONStr.length() > 0) {
            try {
                Gson gson = new Gson();
                productList =
                        gson.fromJson(productJSONStr, new TypeToken<ArrayList<ProductService>>() {
                        }.getType());
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        return productList;
    }
}