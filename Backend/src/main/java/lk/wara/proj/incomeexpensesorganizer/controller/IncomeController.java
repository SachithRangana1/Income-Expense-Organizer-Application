package lk.wara.proj.incomeexpensesorganizer.controller;

import jakarta.persistence.EntityNotFoundException;
import lk.wara.proj.incomeexpensesorganizer.dto.IncomeDto;
import lk.wara.proj.incomeexpensesorganizer.entity.Income;
import lk.wara.proj.incomeexpensesorganizer.service.income.IncomeService;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("app/income")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping
    public ResponseEntity<?> postIncome(@RequestBody IncomeDto incomeDto){
        Income createIncome = incomeService.postIncome(incomeDto);
        if (createIncome != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createIncome);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllIncome(){
        return ResponseEntity.ok(incomeService.getAllIncome());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIncomeById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(incomeService.getIncomeById(id));
        } catch (EntityNotFoundException ef) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ef.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIncome(@PathVariable Long id, @RequestBody IncomeDto dto){
        try {
            return ResponseEntity.ok(incomeService.updateIncome(id, dto));
        } catch (EntityNotFoundException ef) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ef.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable Long id){
        try {
            incomeService.deleteIncome(id);
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        } catch (EntityNotFoundException ef) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Something went wrong"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Deletion failed"));
        }
    }
}
