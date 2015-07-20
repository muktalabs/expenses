package com.muktalabs.em.dao;

import java.util.List;

import com.muktalabs.em.model.ExpenseType;
import com.muktalabs.em.model.User;

public interface ExpenseTypeDao {
	public String save(ExpenseType expenseType);

	public String update(ExpenseType expenseType);

	public List<ExpenseType> list(int startIndex, int pageSize, User user);

	public List<ExpenseType> list(ExpenseType criteria);

	public List<ExpenseType> listByExpenseTypeId(String expenseTypeId, User user);

	public ExpenseType getById(String id, User user);

	public String delete(String id, User user);
}
