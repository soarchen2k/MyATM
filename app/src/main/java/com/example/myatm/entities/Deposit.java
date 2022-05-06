package com.example.myatm.entities;

public class Deposit {
    public static void deposit(Account account) {
        if (account.getMoneyInHand() >= account.getCurrentAmount()) {
            account.setBalance(account.getBalance() + account.getCurrentAmount());
            account.setMoneyInHand(account.getMoneyInHand() - account.getCurrentAmount());
        }
    }
}
