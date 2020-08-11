package com.huang.store.service.imp;

import com.huang.store.entity.order.Expense;
import org.springframework.stereotype.Service;

/**
 * @author: 黄龙
 * @date: 2020/7/20 16:48
 * @description:
 */
@Service
public interface ExpenseService {
    int addExpense(Expense expense);
}
