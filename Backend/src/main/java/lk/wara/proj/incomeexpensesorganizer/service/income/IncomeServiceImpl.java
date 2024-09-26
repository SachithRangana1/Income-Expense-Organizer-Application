package lk.wara.proj.incomeexpensesorganizer.service.income;

import jakarta.persistence.EntityNotFoundException;
import lk.wara.proj.incomeexpensesorganizer.dto.IncomeDto;
import lk.wara.proj.incomeexpensesorganizer.entity.Income;
import lk.wara.proj.incomeexpensesorganizer.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService{

    private final IncomeRepository incomeRepository;

    private Income saveOrUpdateIncome(Income income, IncomeDto incomeDto){
        income.setTitle(incomeDto.getTitle());
        income.setDate(incomeDto.getDate());
        income.setAmount(incomeDto.getAmount());
        income.setCategory(incomeDto.getCategory());
        income.setDescription(incomeDto.getDescription());

        return incomeRepository.save(income);
    }

    @Override
    public Income postIncome(IncomeDto incomeDto){
        return saveOrUpdateIncome(new Income(), incomeDto);
    }

    @Override
    public List<Income> getAllIncome(){
        return incomeRepository.findAll().stream().sorted(Comparator.comparing(Income::getDate).reversed()).collect(Collectors.toList());
    }

    @Override
    public Income getIncomeById(Long id){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (optionalIncome.isPresent()){
            return optionalIncome.get();
        }else {
            throw new EntityNotFoundException("Income is not present with id" + id);
        }
    }

    @Override
    public Income updateIncome(Long id, IncomeDto incomeDto){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (optionalIncome.isPresent()){
            return saveOrUpdateIncome(optionalIncome.get(), incomeDto);
        }else {
            throw new EntityNotFoundException("Income is not present with id" + id);
        }
    }

    @Override
    public void deleteIncome(@PathVariable Long id){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (optionalIncome.isPresent()){
            incomeRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Something went wrong");
        }
    }
}
