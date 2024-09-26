package lk.wara.proj.incomeexpensesorganizer.service.income;

import lk.wara.proj.incomeexpensesorganizer.dto.IncomeDto;
import lk.wara.proj.incomeexpensesorganizer.entity.Income;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IncomeService {

    Income postIncome(IncomeDto incomeDto);

    List<Income> getAllIncome();

    Income getIncomeById(Long id);

    Income updateIncome(Long id, IncomeDto incomeDto);

    void deleteIncome(@PathVariable Long id);
}
