/*
 * Copyright 2018 Lithium Technologies Pvt Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lithium.community.android.example;

import android.app.Application;
import android.util.Log;

import com.lithium.community.android.auth.LiAppCredentials;
import com.lithium.community.android.example.utils.MiscUtils;
import com.lithium.community.android.exception.LiInitializationException;
import com.lithium.community.android.manager.LiSDKManager;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("LiExampleApp", "Welcome to your community.");
        try {
            if (MiscUtils.areCredentialsProvided(this)) {
                LiAppCredentials credentials = new LiAppCredentials(
                        getString(R.string.clientName),
                        getString(R.string.clientId),
                        getString(R.string.clientSecret),
                        getString(R.string.tenantId),
                        getString(R.string.communityUrl),
                        MiscUtils.getInstanceId(this)
                );
                LiSDKManager.initialize(this, credentials);
                LiSDKManager.getInstance().syncWithCommunity(this);
                Log.i(MainApplication.class.getSimpleName(), "Lithium SDK initialized successfully");
            } else {
                Log.e(MainApplication.class.getSimpleName(), "Lithium SDK Credentials not set");
            }
        } catch (LiInitializationException e) {
            Log.e(MainApplication.class.getSimpleName(), "Lithium SDK initialization failed.");
            e.printStackTrace();
        }
    }
}
