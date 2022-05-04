package com.example.myatm.entities;

public class Withdraw {
    public static void withdraw(Account account) {
        if (account.getBalance() >= 1000 && account.getBalance() >= account.getAmount()) {
            account.setBalance(account.getBalance() - account.getAmount());
            account.setMoneyInHand(account.getMoneyInHand() + account.getAmount());
        }
    }
}
