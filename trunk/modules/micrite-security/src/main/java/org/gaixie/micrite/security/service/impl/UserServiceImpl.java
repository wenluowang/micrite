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

import java.util.List;
import java.util.Random;

import org.gaixie.micrite.beans.User;
import org.gaixie.micrite.security.dao.IUserDao;
import org.gaixie.micrite.security.service.IUserService;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class UserServiceImpl implements IUserService {

	private IUserDao userDao;
    
	public User findByNameRandom(){
		List<User> list = userDao.findAll();
		Random rnd = new Random();
		int getNumber = rnd.nextInt(list.size());
		return list.get(getNumber);
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

}