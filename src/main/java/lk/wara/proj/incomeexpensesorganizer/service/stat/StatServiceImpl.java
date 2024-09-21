package lk.wara.proj.incomeexpensesorganizer.service.stat;

import lk.wara.proj.incomeexpensesorganizer.dto.GraphDto;
import lk.wara.proj.incomeexpensesorganizer.dto.StatDto;
import lk.wara.proj.incomeexpensesorganizer.entity.Expense;
import lk.wara.proj.incomeexpensesorganizer.entity.Income;
import lk.wara.proj.incomeexpensesorganizer.repository.ExpenseRepository;
import lk.wara.proj.incomeexpensesorganizer.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService{

    private final IncomeRepository incomeRepository;

    private final ExpenseRepository expenseRepository;

    @Override
    public GraphDto getChartData(){
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(27);

        GraphDto graphDto = new GraphDto();
        graphDto.setIncomeList(incomeRepository.findByDateBetween(startDate, endDate));
        graphDto.setExpenseList(expenseRepository.findByDateBetween(startDate, endDate));

        return graphDto;
    }

    @Override
    public StatDto getStat(){
        Double totalIncome = incomeRepository.sumAllAmount();
        Double totalExpense = incomeRepository.sumAllAmount();

        Optional<Income> optionalIncome = incomeRepository.findFirstByOrderByDateDesc();
        Optional<Expense> optionalExpense = expenseRepository.findFirstByOrderByDateDesc();

        StatDto statDto = new StatDto();
        statDto.setExpense(totalExpense);
        statDto.setIncome(totalIncome);

        optionalIncome.ifPresent(statDto::setLatestIncome);
        optionalExpense.ifPresent(statDto::setLatestExpense);

        statDto.setBalance(totalIncome-totalExpense);

        List<Income> incomeList = incomeRepository.findAll();
        List<Expense> expenseList = expenseRepository.findAll();

        OptionalDouble minIncome = incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max();

        OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense::getAmount).max();

        statDto.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble(): null);
        statDto.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble(): null);
        statDto.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : null);
        statDto.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);

        return statDto;
    }
}
