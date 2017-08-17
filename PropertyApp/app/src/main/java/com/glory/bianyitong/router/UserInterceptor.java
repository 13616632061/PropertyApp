package com.glory.bianyitong.router;

import android.content.Context;

import com.chenenyu.router.RouteInterceptor;
import com.chenenyu.router.RouteRequest;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Interceptor;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.util.TextUtil;

/**
 * Created by Administrator on 2017/6/27.
 */

@Interceptor("UserInterceptor")
public class UserInterceptor implements RouteInterceptor {
    @Override
    public boolean intercept(Context context, RouteRequest routeRequest) {
        if((!TextUtil.isEmpty(Database.accessToken)) && Database.accessToken.equals("0")){
            Router.build(RouterMapping.ROUTER_ACTIVITY_LOGIN).requestCode(10).go(context);
            return true;
        }
        return false;
    }
}
