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

package com.lithium.community.android.exception;

import com.lithium.community.android.exception.LiSDKErrorCodes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by shoureya.kant on 12/5/16.
 */

public class LiSDKErrorCodesTest {

    @Test
    public void codesTest() {
        assertEquals(100, LiSDKErrorCodes.NETWORK_ERROR);
        assertEquals(101, LiSDKErrorCodes.JSON_SERIALIZATION_ERROR);
        assertEquals(102, LiSDKErrorCodes.ILLEGAL_ARG_ERROR);
        assertEquals(103, LiSDKErrorCodes.RUNTIME_ERROR);
    }
}
