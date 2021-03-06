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
 *
 * Created by ian on 27/03/2016.
 */
package com.pungwe.cms.core.annotations.stereotypes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p>Defines a theme region within a theme. This region is used
 * not only for rendering but administration purposes as well.</p>
 *
 * <p>The label is used for defining the name of the region in
 * the administration area</p>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ThemeRegion {
    /**
     * The machine name for the region (used in code).
     *
     * @return the name of the region.
     */
    String name();

    /**
     * The region label, used to identify a region via the interface.
     *
     * @return a string with the region label.
     */
    String label();
}
