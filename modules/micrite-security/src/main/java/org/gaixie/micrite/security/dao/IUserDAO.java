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

package org.gaixie.micrite.security.dao;

import java.util.List;

import org.gaixie.micrite.beans.User;
import org.gaixie.micrite.dao.IGenericDAO;

/**
 * 提供与<code>User</code>对象有关的DAO接口。
 * 
 */
public interface IUserDAO extends IGenericDAO<User, Integer>{
    
    /**
     * 根据用户名查询用户。
     * 
     * @see org.gaixie.micrite.beans.User
     * @param username 用户名
     * @return <code>User</code>对象
     */
    public User findByUsername(String username);

    /**
     * 根据用户名查询用户的总数（模糊查询）。
     * 
     * @see org.gaixie.micrite.beans.User
     * @param fullName 真实姓名
     */
    public Integer findByFullnameVagueCount(String fullName);

    /**
     * 根据用户名查询用户集合（模糊查询，分页）。
     * 
     * @see org.gaixie.micrite.beans.User
     * @param fullName 真实姓名
     * @param start 起始索引
     * @param limit 限制数
     * @return <code>User</code>对象列表
     */
    public List<User> findByFullnameVaguePerPage(String fullName, int start, int limit);
    
    /**
     * 根据角色查询用户集合（分页）。
     * 
     * @see org.gaixie.micrite.beans.User
     * @param roleId 角色id
     * @param start 起始索引
     * @param limit 限制数
     * @return <code>User</code>对象列表
     */  
    public List<User> findByRoleIdPerPage(int roleId, int start, int limit);

    /**
     * 根据用角色查询用户的总记录数。
     * 
     * @see org.gaixie.micrite.beans.User
     * @param roleId 角色id
     */     
    public Integer findByRoleIdCount(int roleId);
}
