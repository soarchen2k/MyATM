package com.example.myatm.entities;

public class Deposit {
    public static void deposit(Account account) {
        if (account.getMoneyInHand() >= account.getAmount()) {
            account.setBalance(account.getBalance() + account.getAmount());
            account.setMoneyInHand(account.getMoneyInHand() - account.getAmount());
        }
    }
}
