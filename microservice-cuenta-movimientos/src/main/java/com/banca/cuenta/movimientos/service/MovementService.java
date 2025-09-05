package com.banca.cuenta.movimientos.service;

import com.banca.cuenta.movimientos.dto.CustomerReportDTO;
import java.time.LocalDate;
import java.util.List;

public interface MovementService {


    List<CustomerReportDTO> getCustomerReport(Long idCliente, LocalDate fechaInicio, LocalDate fechaFin);
}