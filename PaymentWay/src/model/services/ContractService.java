package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.time.LocalDate;


public class ContractService {
    OnlinePaymentService paymentService;


    public ContractService(OnlinePaymentService paymentService) {
        this.paymentService = paymentService;
    }


    public void processContract(Contract contract,int months){
        double valueMonths = contract.getTotalValue() / months;

        for (int i = 1; i <= months; i++) {
            LocalDate dueDate = contract.getDate().plusMonths(i);

            double interest = paymentService.interest(valueMonths, i);
            double fee = paymentService.paymentFee(interest + valueMonths);
            double quota =valueMonths + interest + fee;

            contract.getInstallmentList().add(new Installment(dueDate,quota));
        }
    }
}
