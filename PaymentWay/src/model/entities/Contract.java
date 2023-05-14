package model.entities;

import model.services.ContractService;

import java.time.LocalDate;

public class Contract {
    private int number;
    private LocalDate date;
    private double totalValue;

    private Installment installment;
    private ContractService service;

    public Contract(){}

    public Contract(int number, LocalDate date, double totalValue) {
        this.number = number;
        this.date = date;
        this.totalValue = totalValue;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public double getTotalValue() {
        return totalValue;
    }
    public Installment getInstallment() {
        return installment;
    }
    public ContractService getService() {
        return service;
    }
}
