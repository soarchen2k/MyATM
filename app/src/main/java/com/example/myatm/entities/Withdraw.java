package com.example.myatm.entities;

public class Withdraw {
    public static void withdraw(Account account) {
        if (account.getBalance() >= 1000 && account.getBalance() >= account.getCurrentAmount()) {
            account.setBalance(account.getBalance() - account.getCurrentAmount());
            account.setMoneyInHand(account.getMoneyInHand() + account.getCurrentAmount());
        }
    }
}
