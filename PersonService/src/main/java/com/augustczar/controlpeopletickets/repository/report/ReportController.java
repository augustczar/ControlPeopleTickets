package com.augustczar.controlpeopletickets.repository.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.augustczar.controlpeopletickets.service.report.ReportService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/relatorios")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/pessoas")
    public void exportPessoaReport(HttpServletResponse response) throws Exception {
        reportService.exportPessoaReport(response);
    }
}

