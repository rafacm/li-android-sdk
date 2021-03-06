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

package com.lithium.community.android.model.post;

import com.google.gson.JsonObject;
import com.lithium.community.android.model.post.LiGenericPostModel;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by shoureya.kant on 12/21/16.
 */

public class LiGenericPostModelTest {

    private JsonObject data;

    private LiGenericPostModel liGenericPostModel;

    @Test
    public void testParams() {
        data = new JsonObject();
        data.addProperty("test", "test value");
        liGenericPostModel = new LiGenericPostModel();
        liGenericPostModel.setData(data);
        Assert.assertEquals("test value", liGenericPostModel.getData().getAsJsonObject().get("test").getAsString());
    }

}
