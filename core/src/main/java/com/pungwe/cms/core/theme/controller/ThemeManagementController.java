package com.pungwe.cms.core.theme.controller;

import com.pungwe.cms.core.annotations.ui.MenuItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.Callable;

import static com.pungwe.cms.core.utils.Utils.translate;

/**
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
 * Created by ian on 28/03/2016.
 */
@MenuItem(menu = "system", parent = "admin", name = "appearance", title = "Appearance", description = "Manage the appearance of your website", weight = -160)
@Controller
@RequestMapping("/admin/appearance")
public class ThemeManagementController {

    @ModelAttribute("title")
    public String title() {
        return translate("Appearance");
    }

    @RequestMapping(method = RequestMethod.GET)
    public Callable<String> get(Model model) {
        return () -> {
            return "admin/appearance/index";
        };
    }
}
