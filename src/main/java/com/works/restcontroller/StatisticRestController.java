package com.works.restcontroller;

import com.works.dto.rest.StatisticRestDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rest/statistic")
@Api(value = "Statistic Rest Controller", authorizations = {@Authorization(value = "basicAuth")})

public class StatisticRestController {
    final StatisticRestDto statisticRestDto;

    public StatisticRestController(StatisticRestDto statisticRestDto) {
        this.statisticRestDto = statisticRestDto;
    }

    @ApiOperation(value = "İstatistik Servisi")
    @GetMapping("")
    public Map<Object, Object> statistic() {
        return statisticRestDto.statistic();
    }

}