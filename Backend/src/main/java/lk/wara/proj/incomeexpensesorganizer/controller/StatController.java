package lk.wara.proj.incomeexpensesorganizer.controller;

import lk.wara.proj.incomeexpensesorganizer.dto.GraphDto;
import lk.wara.proj.incomeexpensesorganizer.service.stat.StatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/stat")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StatController {

    private final StatService statService;

    @GetMapping("/chart")
    public ResponseEntity<GraphDto> getChartDetails(){
        return ResponseEntity.ok(statService.getChartData());
    }

    @GetMapping
    public ResponseEntity<?> getStat(){
        return ResponseEntity.ok(statService.getStat());
    }
}
