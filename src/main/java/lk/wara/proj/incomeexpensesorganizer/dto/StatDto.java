package lk.wara.proj.incomeexpensesorganizer.dto;

import lk.wara.proj.incomeexpensesorganizer.entity.Expense;
import lk.wara.proj.incomeexpensesorganizer.entity.Income;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StatDto {

    private Double Income;

    private Double Expense;

    private Income latestIncome;

    private Expense latestExpense;

    private Double balance;

    private Double minIncome;

    private Double maxIncome;

    private Double minExpense;

    private Double maxExpense;
}
