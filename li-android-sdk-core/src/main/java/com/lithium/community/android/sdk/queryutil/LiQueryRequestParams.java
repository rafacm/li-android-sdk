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

package com.lithium.community.android.sdk.queryutil;

import com.lithium.community.android.sdk.manager.LiClientManager;
import com.lithium.community.android.sdk.utils.LiQueryConstant;

import java.util.ArrayList;

/**
 * This class is used to process the where clause, liQueryOrdering and limit of the LIQL query.
 * It takes from user the fields for where clause and completes the query. It also handles liQueryOrdering.
 * Created by kunal.shrivastava on 12/22/16.
 */

public class LiQueryRequestParams {

    private LiClientManager.Client client;
    private LiQueryOrdering liQueryOrdering;
    private LiQueryWhereClause liQueryWhereClause;
    private int limit;

    private LiQueryRequestParams(Builder builder) {
        this.client = builder.client;
        this.liQueryOrdering = builder.liQueryOrdering;
        this.liQueryWhereClause = builder.liQueryWhereClause;
        this.limit = builder.limit;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public LiClientManager.Client getClient() {
        return client;
    }

    /**
     * Computes where clause, liQueryOrdering and limit.
     *
     * @return Model representing setting for API provider query.
     */
    public LiQuerySetting getQuerySetting() {

        ArrayList<LiQuerySetting.WhereClause> whereClauses = new ArrayList();
        for (LiQueryClause liQueryClause : liQueryWhereClause.getLiQueryClauses()) {
            LiQuerySetting.WhereClause whereClause = new LiQuerySetting.WhereClause();
            whereClause.setClause(liQueryClause.getClause());
            whereClause.setKey(liQueryClause.getKey().getValue());
            whereClause.setValue(liQueryClause.getValue());
            whereClause.setOperator(liQueryClause.getOperator());
            whereClauses.add(whereClause);
        }

        LiQuerySetting.Ordering ordering = null;
        if (this.liQueryOrdering != null) {
            ordering = new LiQuerySetting.Ordering();
            ordering.setKey(this.liQueryOrdering.getColumn().getValue());
            ordering.setType(this.liQueryOrdering.getOrder().name());
        }

        if (limit < 1) {
            limit = LiQueryConstant.DEFAULT_LIQL_QUERY_LIMIT;
        }
        LiQuerySetting liQuerySetting = new LiQuerySetting(whereClauses, ordering, limit);
        return liQuerySetting;
    }

    /**
     * Builder for {@link LiQueryRequestParams}
     */
    public static class Builder {

        private LiClientManager.Client client;
        private LiQueryOrdering liQueryOrdering;
        private LiQueryWhereClause liQueryWhereClause;
        private int limit;

        public Builder setClient(LiClientManager.Client client) {
            this.client = client;
            return this;
        }

        public Builder setLiQueryOrdering(LiQueryOrdering liQueryOrdering) {
            this.liQueryOrdering = liQueryOrdering;
            return this;
        }

        public Builder setLiQueryWhereClause(LiQueryWhereClause liQueryWhereClause) {
            this.liQueryWhereClause = liQueryWhereClause;
            return this;
        }

        public Builder setLimit(int limit) {
            this.limit = limit;
            return this;
        }

        public LiQueryRequestParams build() {
            for (LiQueryClause liQueryClause : liQueryWhereClause.getLiQueryClauses()) {
                if (!liQueryClause.isValid(client)) {
                    throw new RuntimeException(String.format("Invalid liQueryClause !!! LiQueryClause of %s used in %s",
                            liQueryClause.getKey().getClient(), client));
                }
            }
            if (!liQueryOrdering.isVaild(client)) {
                throw new RuntimeException(
                        String.format("Invalid liQueryOrdering !!! Use LiQueryOrdering of  %s", client));
            }
            return new LiQueryRequestParams(this);
        }
    }
}