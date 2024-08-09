package com.augustczar.controlpeopletickets.service.impl.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.augustczar.controlpeopletickets.model.Pessoa;
import com.augustczar.controlpeopletickets.repository.PessoaRepository;
import com.augustczar.controlpeopletickets.service.report.ReportService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportServiceImpl implements ReportService{

	 @Autowired
	    private PessoaRepository pessoaRepository;

	    public void exportPessoaReport(HttpServletResponse response) throws Exception {
	        List<Pessoa> pessoas = pessoaRepository.findAll();

	        // Gerar o relatório com JasperReports
	        JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/pessoas.jrxml"));
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(pessoas);
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy", "ControlPeopleTickets");

	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

	        // Exportar para PDF
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment; filename=pessoas.pdf");

	        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	    }
}
