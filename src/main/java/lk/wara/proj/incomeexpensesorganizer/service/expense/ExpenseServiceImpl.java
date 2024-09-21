package lk.wara.proj.incomeexpensesorganizer.service.expense;

import jakarta.persistence.EntityNotFoundException;
import lk.wara.proj.incomeexpensesorganizer.dto.ExpenseDto;
import lk.wara.proj.incomeexpensesorganizer.entity.Expense;
import lk.wara.proj.incomeexpensesorganizer.repository.ExpenseRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{

    private final ExpenseRepository expenseRepository;

    private Expense saveorUpdateExpense(Expense expense, ExpenseDto expenseDto){
        expense.setTitle(expenseDto.getTitle());
        expense.setDate(expenseDto.getDate());
        expense.setAmount(expenseDto.getAmount());
        expense.setCategory(expenseDto.getCategory());
        expense.setDescription(expenseDto.getDescription());

        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll().stream().sorted(Comparator.comparing(Expense::getDate).reversed()).collect(Collectors.toList());
    }

    @Override
    public Expense postExpense(ExpenseDto expenseDto) {
        return saveorUpdateExpense(new Expense(), expenseDto);

    }

    @Override
    public Expense getExpenseById(Long id){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()){
            return optionalExpense.get();
        }else {
            throw new EntityNotFoundException("Expense is not present with the id" + id);
        }
    }

    @Override
    public Expense updateExpense(long id, ExpenseDto expenseDto){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()){
            return saveorUpdateExpense(optionalExpense.get(), expenseDto);
        }else {
            throw new EntityNotFoundException("Expense is not present with id" + id);
        }
    }

    @Override
    public void deleteExpense(Long id){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()){
            expenseRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Expense is not present with id" + id);
        }


    }
}
