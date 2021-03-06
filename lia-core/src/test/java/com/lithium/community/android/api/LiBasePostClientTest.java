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

package com.lithium.community.android.api;

import android.app.Activity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.lithium.community.android.api.LiBaseClient;
import com.lithium.community.android.api.LiBasePostClient;
import com.lithium.community.android.exception.LiRestResponseException;
import com.lithium.community.android.rest.LiRestv2Client;

import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * Created by kunal.shrivastava on 12/1/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(LiRestv2Client.class)
public class LiBasePostClientTest {

    private static final String BASE_PATH = "http://localhost/";
    private LiBasePostClient liClient;
    private LiRestv2Client liRestv2Client;
    private Activity mContext;

    @Before
    public void setUp() throws LiRestResponseException {
        mContext = Mockito.mock(Activity.class);
        liRestv2Client = mock(LiRestv2Client.class);
        PowerMockito.mockStatic(LiRestv2Client.class);
        BDDMockito.given(LiRestv2Client.getInstance()).willReturn(liRestv2Client);
        liClient = new LiBasePostClient(mContext, BASE_PATH);
        PowerMockito.verifyStatic();
    }

    @Test
    public void testBasePostClientCreation() throws LiRestResponseException {
        Assert.assertEquals(null, liClient.querySettingsType);
        Assert.assertEquals(null, liClient.type);
        Assert.assertEquals(BASE_PATH, liClient.basePath);
        Assert.assertEquals(LiBaseClient.RequestType.POST, liClient.requestType);
    }

}
