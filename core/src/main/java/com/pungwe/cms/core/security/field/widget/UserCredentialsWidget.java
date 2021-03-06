/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * )with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * Created by ian on 02/04/2016.
 */
package com.pungwe.cms.core.security.field.widget;

import com.pungwe.cms.core.annotations.stereotypes.FieldWidget;
import com.pungwe.cms.core.element.RenderedElement;
import com.pungwe.cms.core.entity.FieldConfig;
import com.pungwe.cms.core.field.FieldWidgetDefinition;
import org.springframework.security.core.userdetails.User;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@FieldWidget(
        value = "user_credentials_widget",
        label = "User Credentials",
        supports = "user_credentials_field"
)
public class UserCredentialsWidget implements FieldWidgetDefinition<User> {

    @Override
    public Map<String, Object> getDefaultSettings() {
        return new LinkedHashMap<>();
    }

    @Override
    public void buildWidgetForm(List<RenderedElement> elements, FieldConfig field, User value, int delta) {
    }

    @Override
    public void buildWidgetSettingsForm(List<RenderedElement> elements, Map<String, Object> settings) {
    }

    @Override
    public User extractValueFromForm(FieldConfig field, Map<String, Object> values, int delta) {
        return null;
    }
}
