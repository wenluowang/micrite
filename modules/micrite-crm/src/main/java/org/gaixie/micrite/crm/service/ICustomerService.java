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

package org.gaixie.micrite.crm.service;

import java.util.List;

import org.gaixie.micrite.beans.Customer;
import org.gaixie.micrite.beans.CustomerSource;

/**
 * 客户管理服务接口，封装客户服务业务模型
 */
public interface ICustomerService {
	/**
	 * 新增或修改客户
	 * @see org.gaixie.micrite.beans.Customer
	 * @param customer 客户实体
	 * @param customerSourceId 客户来源id
	 */
	public void addOrUpdateCustomer(Customer customer, Integer customerSourceId);

	/**
	 * 获取客户数量
	 * @return 客户数量
	 */
	public int getCustomerNum();

	/**
	 * 根据电话精确查找客户
	 * @see org.gaixie.micrite.beans.Customer
	 * @param telephone 客户电话
	 * @return 客户实体集合
	 */
	public List<Customer> findByTelExact(String telephone);

	/**
	 * 根据电话模糊查找客户
	 * @see org.gaixie.micrite.beans.Customer
	 * @param telephone 客户电话
	 * @return 客户实体集合
	 */
	public List<Customer> findByTelVague(String telephone);

	/**
	 * 根据客户ID获取客户
	 * @see org.gaixie.micrite.beans.Customer
	 * @param id 客户id
	 * @return 客户实体
	 */
	public Customer findByIdExact(int id);

	/**
	 * 获取客户来源
	 * @see org.gaixie.micrite.beans.CustomerSource
	 * @return 客户来源集合
	 */
	public List<CustomerSource> findALLCustomerSource();

}
