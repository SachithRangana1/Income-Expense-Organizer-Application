package lk.wara.proj.incomeexpensesorganizer.dto;

import lk.wara.proj.incomeexpensesorganizer.entity.Expense;
import lk.wara.proj.incomeexpensesorganizer.entity.Income;
import lombok.Data;

import java.util.List;

@Data
public class GraphDto {
    private List<Income> incomeList;

    private List<Expense> expenseList;
}
