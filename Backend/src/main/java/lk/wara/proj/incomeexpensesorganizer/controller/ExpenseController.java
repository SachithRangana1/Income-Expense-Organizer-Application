package lk.wara.proj.incomeexpensesorganizer.controller;

import jakarta.persistence.EntityNotFoundException;
import lk.wara.proj.incomeexpensesorganizer.dto.ExpenseDto;
import lk.wara.proj.incomeexpensesorganizer.entity.Expense;
import lk.wara.proj.incomeexpensesorganizer.service.expense.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("app/expense")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<?> postExpense(@RequestBody ExpenseDto dto){
        Expense createdExpense = expenseService.postExpense(dto);
        if (createdExpense != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllExpenses(){
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(expenseService.getExpenseById(id));
        } catch (EntityNotFoundException ef) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ef.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEntity(@PathVariable Long id, @RequestBody ExpenseDto dto){
        try {
            return ResponseEntity.ok(expenseService.updateExpense(id,dto));
        }catch (EntityNotFoundException ef){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ef.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntity(@PathVariable Long id){
        try {
            expenseService.deleteExpense(id);
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        }catch (EntityNotFoundException ef){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Expense not found"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Something went wrong"));
        }
    }

}
