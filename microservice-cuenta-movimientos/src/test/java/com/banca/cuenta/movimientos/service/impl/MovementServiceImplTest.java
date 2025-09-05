package com.banca.cuenta.movimientos.service.impl;

import com.banca.cuenta.movimientos.dto.CustomerReportDTO;
import com.banca.cuenta.movimientos.client.CustomerClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovementServiceImplTest {
    @Mock
    private EntityManager entityManager;
    @Mock
    private CustomerClient customerClient;
    @Mock
    private TypedQuery<Object[]> typedQuery;

    @InjectMocks
    private MovementServiceImpl movementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomerReport_ReturnsReport() {
        Long idCliente1 = 1L;
        LocalDate fechaInicio1 = LocalDate.of(2023, 1, 1);
        LocalDate fechaFin1 = LocalDate.of(2023, 12, 31);
        Object[] row1 = new Object[]{1000.0, "Juan Perez", LocalDate.of(2023, 5, 10), "Ahorro", "Deposito", 1200.0, "1234567890", "Activo", "Crédito"};
    List<Object[]> results1 = Arrays.<Object[]>asList(row1);
        when(entityManager.createQuery(anyString(), eq(Object[].class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("idCliente"), any())).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("inicio"), any())).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("fin"), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(results1);
        List<CustomerReportDTO> report1 = movementService.getCustomerReport(idCliente1, fechaInicio1, fechaFin1);
        assertNotNull(report1);
        assertEquals(1, report1.size());
        CustomerReportDTO dto1 = report1.get(0);
        assertEquals("Juan Perez", dto1.getName());
        assertEquals(1000.0, dto1.getInitialBalance());
        assertEquals(LocalDate.of(2023, 5, 10), dto1.getDate());
        assertEquals("Ahorro", dto1.getParameterValue());
        assertEquals("Deposito", dto1.getTransactionTypeAmount());
        assertEquals(1200.0, dto1.getBalance());
        assertEquals("1234567890", dto1.getAccountNumber());
        assertEquals("Activo", dto1.getCustomerStatus());
    assertEquals("Crédito", dto1.getMovementType());
    }

    @Test
    void testGetCustomerReport_Credito() {
        Long clientId2 = 1L;
        LocalDate startDate2 = LocalDate.of(2023, 1, 1);
        LocalDate endDate2 = LocalDate.of(2023, 12, 31);
        Object[] row2 = new Object[]{1000.0, "Juan Perez", LocalDate.of(2023, 5, 10), "Ahorro", "DEPOSITO", 1200.0, "1234567890", "Activo", null};
    List<Object[]> results2 = Arrays.<Object[]>asList(row2);
        when(entityManager.createQuery(anyString(), eq(Object[].class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("clientId"), any())).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("startDate"), any())).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("endDate"), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(results2);
        List<CustomerReportDTO> report2 = movementService.getCustomerReport(clientId2, startDate2, endDate2);
        assertNotNull(report2);
        assertEquals(1, report2.size());
        CustomerReportDTO dto2 = report2.get(0);
        assertEquals("Crédito", dto2.getMovementType());
    }

    @Test
    void testGetCustomerReport_Debito() {
        Long clientId3 = 2L;
        LocalDate startDate3 = LocalDate.of(2023, 1, 1);
        LocalDate endDate3 = LocalDate.of(2023, 12, 31);
        Object[] row3 = new Object[]{2000.0, "Maria Lopez", LocalDate.of(2023, 6, 15), "Corriente", "RETIRO", 1800.0, "0987654321", "Activo", null};
    List<Object[]> results3 = Arrays.<Object[]>asList(row3);
        when(entityManager.createQuery(anyString(), eq(Object[].class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("clientId"), any())).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("startDate"), any())).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("endDate"), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(results3);
        List<CustomerReportDTO> report3 = movementService.getCustomerReport(clientId3, startDate3, endDate3);
        assertNotNull(report3);
        assertEquals(1, report3.size());
        CustomerReportDTO dto3 = report3.get(0);
        assertEquals("Débito", dto3.getMovementType());
    }

    @Test
    void testGetCustomerReport_Otro() {
        Long clientId4 = 3L;
        LocalDate startDate4 = LocalDate.of(2023, 1, 1);
        LocalDate endDate4 = LocalDate.of(2023, 12, 31);
        Object[] row4 = new Object[]{3000.0, "Carlos Ruiz", LocalDate.of(2023, 7, 20), "Corriente", "TRANSFERENCIA", 3200.0, "1122334455", "Activo", null};
    List<Object[]> results4 = Arrays.<Object[]>asList(row4);
        when(entityManager.createQuery(anyString(), eq(Object[].class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("clientId"), any())).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("startDate"), any())).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("endDate"), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(results4);
        List<CustomerReportDTO> report4 = movementService.getCustomerReport(clientId4, startDate4, endDate4);
        assertNotNull(report4);
        assertEquals(1, report4.size());
        CustomerReportDTO dto4 = report4.get(0);
        assertEquals("Otro", dto4.getMovementType());
    }
}