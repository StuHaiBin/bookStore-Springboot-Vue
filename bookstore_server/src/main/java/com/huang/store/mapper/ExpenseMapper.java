package com.huang.store.mapper;

import com.huang.store.entity.order.Expense;
import org.springframework.stereotype.Repository;

/**
 * @author: 黄龙
 * @date: 2020/7/20 16:43
 * @description:
 */
@Repository
public interface ExpenseMapper {
    int addExpense(Expense expense);
}
