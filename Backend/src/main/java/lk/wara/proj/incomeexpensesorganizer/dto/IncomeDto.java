package lk.wara.proj.incomeexpensesorganizer.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class IncomeDto {
    private long id;

    private String title;

    private Double amount;

    private LocalDate date;

    private String category;

    private String description;
}
