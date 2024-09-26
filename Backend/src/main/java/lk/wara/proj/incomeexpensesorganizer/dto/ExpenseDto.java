package lk.wara.proj.incomeexpensesorganizer.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseDto {

    private long id;
    private String title;
    private String description;
    private String category;
    private LocalDate date;
    private Double amount;
}
