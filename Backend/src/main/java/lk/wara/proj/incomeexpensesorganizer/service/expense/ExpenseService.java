package lk.wara.proj.incomeexpensesorganizer.service.expense;

import lk.wara.proj.incomeexpensesorganizer.dto.ExpenseDto;
import lk.wara.proj.incomeexpensesorganizer.entity.Expense;

import java.util.List;

public interface ExpenseService {

    Expense postExpense(ExpenseDto expenseDto);

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    Expense updateExpense(long id, ExpenseDto expenseDto);

    void deleteExpense(Long id);
}
