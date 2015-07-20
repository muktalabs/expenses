package com.muktalabs.em.dao;

import java.util.Date;
import java.util.List;

import com.muktalabs.em.model.ExpenseInventory;
import com.muktalabs.em.model.User;

public interface ExpenseInventoryDao {
	public String save(ExpenseInventory expenseInventory);

	public String update(ExpenseInventory expenseInventory);

	public List<ExpenseInventory> list(int startIndex, int pageSize, User user);

	public List<ExpenseInventory> list(ExpenseInventory criteria);

	public List<ExpenseInventory> listByCompanyId(String companyId, User user);
	
	public List<ExpenseInventory> listByDates( Date fromDate, Date toDate, User user );

	public ExpenseInventory getById(String id, User user);

	public String delete(String id, User user);
}
