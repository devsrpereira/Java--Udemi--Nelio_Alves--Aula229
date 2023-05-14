package model.services;

import model.entities.Contract;
import model.entities.Installment;


public class ContractService {
    OnlinePaymentService paymentService;


    public ContractService(OnlinePaymentService paymentService) {
        this.paymentService = paymentService;
    }


    public void processContract(Contract contract,int months){
        double valueMonths = contract.getTotalValue() / months;
        int month = contract.getInstallmentList().size() + 1;
        double interest = paymentService.interest(valueMonths, month );
        double fee = paymentService.paymentFee(interest + valueMonths);

        contract.getInstallmentList().add(new Installment(contract.getDate().plusMonths(month)
                ,(valueMonths + interest + fee)));
    }
}
