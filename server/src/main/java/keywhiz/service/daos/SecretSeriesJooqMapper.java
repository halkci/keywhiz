/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package keywhiz.service.daos;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import keywhiz.api.model.SecretSeries;
import org.jooq.Record;
import org.jooq.RecordMapper;

import static keywhiz.jooq.tables.Secrets.SECRETS;

class SecretSeriesJooqMapper implements RecordMapper<Record, SecretSeries> {
  public SecretSeries map(Record r) {
    Gson gson = new Gson();
    Type optionsType = new TypeToken<HashMap<String, String>>() {}.getType();

    return new SecretSeries(
        r.getValue(SECRETS.ID),
        r.getValue(SECRETS.NAME),
        r.getValue(SECRETS.DESCRIPTION),
        r.getValue(SECRETS.CREATEDAT),
        r.getValue(SECRETS.CREATEDBY),
        r.getValue(SECRETS.UPDATEDAT),
        r.getValue(SECRETS.UPDATEDBY),
        r.getValue(SECRETS.TYPE),
        gson.fromJson(r.getValue(SECRETS.OPTIONS), optionsType));
  }
}
