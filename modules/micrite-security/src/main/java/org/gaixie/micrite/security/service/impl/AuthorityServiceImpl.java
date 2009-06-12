/* ===========================================================
 * $Id$
 * This file is part of Micrite
 * ===========================================================
 *
 * (C) Copyright 2009, by Gaixie.org and Contributors.
 * 
 * Project Info:  http://micrite.gaixie.org/
 *
 * Micrite is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Micrite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Micrite.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.gaixie.micrite.security.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.gaixie.micrite.beans.Authority;
import org.gaixie.micrite.beans.Role;
import org.gaixie.micrite.security.action.LoginAction;
import org.gaixie.micrite.security.dao.IAuthorityDao;
import org.gaixie.micrite.security.dao.IRoleDao;
import org.gaixie.micrite.security.filter.FilterSecurityInterceptor;
import org.gaixie.micrite.security.filter.MethodSecurityInterceptor;
import org.gaixie.micrite.security.service.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.ConfigAttributeEditor;
import org.springframework.security.intercept.web.RequestKey;

/**
 * 
 *
 */
public class AuthorityServiceImpl implements IAuthorityService {

    private static final Logger logger = Logger.getLogger(LoginAction.class);
    @Autowired
    private IAuthorityDao authorityDao;
    @Autowired
    private IRoleDao roleDao;

    public boolean add(Authority authority, String roleIdBunch) {
        authorityDao.save(authority);
        String[] arrRoleId = roleIdBunch.split(",");
        for (int i = 0; i < arrRoleId.length; i++) {
            Role role = roleDao.getRole(Integer.parseInt(arrRoleId[i]));
            role.getAuthorities().add(authority);
            roleDao.save(role);
        }
        if (authority.getType().equals("URL"))
            FilterSecurityInterceptor.refresh();
        else if (authority.getType().equals("METHOD"))
            MethodSecurityInterceptor.refresh();
        return true;
    }

    public LinkedHashMap<RequestKey, ConfigAttributeDefinition> initRequestMap() {
        LinkedHashMap<RequestKey, ConfigAttributeDefinition> requestMap = new LinkedHashMap<RequestKey, ConfigAttributeDefinition>();

        List<Authority> authorities = authorityDao.findByType("URL");
        for (Authority authority : authorities) {
            RequestKey key = new RequestKey(authority.getValue());
            List<String> rolesList = new ArrayList<String>();
            for (Role role : authority.getRoles()) {
                rolesList.add(role.getName());
            }
            String grantedAuthorities = StringUtils.join(rolesList, ",");
            if (grantedAuthorities != null) {
                ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
                configAttrEditor.setAsText(grantedAuthorities);
                ConfigAttributeDefinition definition = (ConfigAttributeDefinition) configAttrEditor
                        .getValue();
                requestMap.put(key, definition);
            }
        }
        return requestMap;
    }

    public LinkedHashMap<String, ConfigAttributeDefinition> initMethodMap() {
        LinkedHashMap<String, ConfigAttributeDefinition> methodMap = new LinkedHashMap<String, ConfigAttributeDefinition>();

        List<Authority> authorities = authorityDao.findByType("METHOD");
        methodMap = new LinkedHashMap<String, ConfigAttributeDefinition>();
        for (Authority authority : authorities) {
            List<String> rolesList = new ArrayList<String>();
            for (Role role : authority.getRoles()) {
                rolesList.add(role.getName());
            }
            String grantedAuthorities = StringUtils.join(rolesList, ",");
            if (grantedAuthorities != null) {
                ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
                configAttrEditor.setAsText(grantedAuthorities);
                ConfigAttributeDefinition definition = (ConfigAttributeDefinition) configAttrEditor
                        .getValue();
                methodMap.put(authority.getValue(), definition);
            }
        }
        return methodMap;
    }
}
