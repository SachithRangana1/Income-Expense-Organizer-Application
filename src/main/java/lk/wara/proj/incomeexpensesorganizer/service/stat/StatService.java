package lk.wara.proj.incomeexpensesorganizer.service.stat;

import lk.wara.proj.incomeexpensesorganizer.dto.GraphDto;
import lk.wara.proj.incomeexpensesorganizer.dto.StatDto;

public interface StatService {

    GraphDto getChartData();

    StatDto getStat();
}
