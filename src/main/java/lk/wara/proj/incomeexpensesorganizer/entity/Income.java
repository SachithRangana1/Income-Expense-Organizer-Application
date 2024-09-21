package lk.wara.proj.incomeexpensesorganizer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private Double amount;

    private LocalDate date;

    private String category;

    private String description;
}
