package com.augustczar.controlpeopletickets.service.report;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {

    public void exportPessoaReport(HttpServletResponse response) throws Exception;
  
}
