package com.banca.cuenta.movimientos.service.impl;

import com.banca.cuenta.movimientos.dto.CustomerReportDTO;
import com.banca.cuenta.movimientos.service.MovementService;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@Service
public class MovementServiceImpl implements MovementService {

    @PersistenceContext
    private EntityManager entityManager;

        @org.springframework.beans.factory.annotation.Autowired
        private com.banca.cuenta.movimientos.client.CustomerClient customerClient;

    @Override
        public List<CustomerReportDTO> getCustomerReport(Long clientId, LocalDate startDate, LocalDate endDate) {
        String jpql = "SELECT act.initialBalance, per.name, mov.date, par.value, mov.transactionTypeAmount, mov.balance, act.number, cust.status " +
            " FROM Movement mov " +
            " JOIN mov.account act " +
            " JOIN act.typeParameter par " +
            " JOIN act.customer cust " +
            " JOIN cust.person per " +
            " WHERE act.customer.id = :clientId " +
            " AND mov.date BETWEEN :startDate AND :endDate";
    TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
    query.setParameter("clientId", clientId);
    query.setParameter("startDate", startDate);
    query.setParameter("endDate", endDate);
        List<Object[]> results = query.getResultList();
        List<CustomerReportDTO> report = new java.util.ArrayList<>();
        for (Object[] row : results) {
            Double initialBalance = (Double) row[0];
            String customerName = (String) row[1];
            java.time.LocalDate date = (row[2] instanceof java.sql.Date)
                ? ((java.sql.Date) row[2]).toLocalDate()
                : (row[2] instanceof java.time.LocalDate)
                    ? (java.time.LocalDate) row[2]
                    : null;
            String parameterValue = (String) row[3];
            String transactionTypeAmount = (String) row[4];
            Double balance = (Double) row[5];
            String accountNumber = (String) row[6];
            String customerStatus = (String) row[7];
            String movementType = "";
            if (transactionTypeAmount != null) {
                if (transactionTypeAmount.toUpperCase().contains("DEPOSITO")) {
                    movementType = "Crédito";
                } else if (transactionTypeAmount.toUpperCase().contains("RETIRO")) {
                    movementType = "Débito";
                } else {
                    movementType = "Otro";
                }
            }
            CustomerReportDTO dto = new CustomerReportDTO(customerName, initialBalance, date, parameterValue, transactionTypeAmount, balance, accountNumber, customerStatus, movementType);
            report.add(dto);
        }
        return report;
    }
}