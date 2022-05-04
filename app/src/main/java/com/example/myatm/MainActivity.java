package com.example.myatm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myatm.entities.Account;
import com.example.myatm.entities.Deposit;
import com.example.myatm.entities.Withdraw;

public class MainActivity extends AppCompatActivity {
    private Account account;
    private Withdraw withdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        account = new Account();
        changeBalanceView();
        changeAmountView();
    }

    public void onDeposit(View view) {
        Deposit.deposit(account);
        changeBalanceView();
    }

    public void onWithdraw(View view) {
        Withdraw.withdraw(account);
        changeBalanceView();
    }

    public void onChangeAmount(View view) {
        account.changeAmount();
        changeAmountView();
    }

    private void changeAmountView() {
        TextView amount = (TextView) findViewById(R.id.change_view);
        amount.setText(String.valueOf(account.getAmount()));
    }

    private void changeBalanceView() {
        TextView balance = (TextView) findViewById(R.id.balance_view);
        balance.setText(String.valueOf(account.getBalance()));
        TextView moneyInHand = (TextView) findViewById(R.id.inhandmoney_view);
        moneyInHand.setText(String.valueOf(account.getMoneyInHand()));
    }
}