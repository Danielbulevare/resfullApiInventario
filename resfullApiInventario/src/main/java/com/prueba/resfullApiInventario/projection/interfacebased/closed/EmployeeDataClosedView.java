package com.prueba.resfullApiInventario.projection.interfacebased.closed;

public interface EmployeeDataClosedView {
    Long getId();
    String getName();
    StatusClosedView getStatus();
    RoleClosedView getRole();
}
