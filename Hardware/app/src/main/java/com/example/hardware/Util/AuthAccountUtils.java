package com.example.hardware.Util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.os.Bundle;

public class AuthAccountUtils {
    public final Account account;  // Account {name=huangfeihongxm@gmail.com, type=com.google}
    public final String authTokenType;  // "oauth2:https://www.googleapis.com/auth/googleplay"
    private final AccountManager accountManager;

    public AuthAccountUtils(Context context, Account account, String authTokenType) {
        this(context, account, authTokenType, (byte)0);
    }

    public AuthAccountUtils(Context context, Account account, String authTokenType, byte arg4) {
        AccountManager accountManager = AccountManager.get(context);
        this.accountManager = accountManager;
        this.account = account;
        this.authTokenType = authTokenType;
    }

    public final void invalidateAuthToken(String arg3) {
        this.accountManager.invalidateAuthToken(this.account.type, arg3);
    }

    public final String getAuthTokenFromAccountManager() {
        String authToken = "";
        Object bundleAuthInfo;  // Bundle[{authAccount=huangfeihongxm@gmail.com, accountType=com.google, authtoken=ya29.Gs8BXgetgaTu1xt_5_q7rpr9FYC0AJdg4K1LwMVscpsS4gLyvPtP3IX6rfQl0zDs5ENlZmaWC9uuvOdw8rDGKJZsOwiEHWyfgHKOVDgaWAvERFsQ5kOJ1TS_YOgcIE2XzfRv8UeWBO6hBGUvEFtwbFREzfeUPNMkBWjotCweyYw5kEHkGt8A7vc6slmqgvu5EtgLlInRii-tgbvAWg5vKmKkmEVCFqMLv17RskUV1Y7AQ9OYTNkcCa0tsoPN7foaGuvZn9Glehn8IoJz6isxlbFj}]
        AccountManagerFuture accountManaerFuture = this.accountManager.getAuthToken(this.account, this.authTokenType, false, null, null);  //  public AccountManagerFuture<Bundle> getAuthToken( /             final Account account, final String authTokenType, /             final boolean notifyAuthFailure, /             AccountManagerCallback<Bundle> callback, Handler handler)
        try {
            bundleAuthInfo = accountManaerFuture.getResult();  // Bundle[{authAccount=huangfeihongxm@gmail.com, accountType=com.google, authtoken=ya29.Gs8BXgetgaTu1xt_5_q7rpr9FYC0AJdg4K1LwMVscpsS4gLyvPtP3IX6rfQl0zDs5ENlZmaWC9uuvOdw8rDGKJZsOwiEHWyfgHKOVDgaWAvERFsQ5kOJ1TS_YOgcIE2XzfRv8UeWBO6hBGUvEFtwbFREzfeUPNMkBWjotCweyYw5kEHkGt8A7vc6slmqgvu5EtgLlInRii-tgbvAWg5vKmKkmEVCFqMLv17RskUV1Y7AQ9OYTNkcCa0tsoPN7foaGuvZn9Glehn8IoJz6isxlbFj}]
            if(accountManaerFuture.isDone()) {
                if(!accountManaerFuture.isCancelled()) {
                    String intentKey = "intent";
                    if(!((Bundle)bundleAuthInfo).containsKey(intentKey)) {
                        authToken = ((Bundle)bundleAuthInfo).getString("authtoken");
                    }
                    else {
                        authToken = "";
                    }
                }
            }
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }

        return authToken;
    }

    public static void testGoogleAccount(Context context){
        Account account = new Account("huangfeihongxm@gmail.com","com.google");
        AuthAccountUtils authAccount = new AuthAccountUtils(context,account,"oauth2:https://www.googleapis.com/auth/googleplay");
        String authToken = authAccount.getAuthTokenFromAccountManager();
        authToken = authToken + "";
    }
}
