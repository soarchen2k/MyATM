package com.example.myatm.entities;

import java.util.Arrays;
import java.util.List;

public class Account {
    private Integer balance;
    private static final List<Integer> AMOUNTS = Arrays.asList(50, 100, 200, 500, 1000);
    private Integer moneyInHand;
    private Integer currentAmount;

    public Account() {
        this.balance = 5000;
        this.moneyInHand = 0;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public List<Integer> getAmounts() {
        return AMOUNTS;
    }

    public Integer getMoneyInHand() {
        return moneyInHand;
    }

    public void setMoneyInHand(Integer moneyInHand) {
        this.moneyInHand = moneyInHand;
    }

    public Integer getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Integer currentAmount) {
        this.currentAmount = currentAmount;
    }
}
