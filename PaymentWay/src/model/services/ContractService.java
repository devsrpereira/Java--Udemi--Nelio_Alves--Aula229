package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContractService {
    OnlinePaymentService paymentService;
    private int months;

    List<Installment> installments = new ArrayList<>();

    public ContractService(OnlinePaymentService paymentService, int months) {
        this.paymentService = paymentService;
        this.months = months;
    }

    public int getMonths() {
        return months;
    }

    public void processContract(Contract contract, int months){
        double valueMonths = contract.getTotalValue() / getMonths();

        for (int i = 0; i < getMonths(); i++) {
            double interest = paymentService.interest(valueMonths, (i+1));
            double fee = paymentService.paymentFee(interest + valueMonths);
            LocalDate dueDate = contract.getDate().plusMonths(i+1);

            Installment installment = new Installment(dueDate, (valueMonths + interest + fee));
            installments.add(installment);
        }
    }
}
