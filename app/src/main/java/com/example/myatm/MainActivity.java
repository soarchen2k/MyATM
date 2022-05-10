package com.example.myatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myatm.entities.Account;
import com.example.myatm.entities.Deposit;
import com.example.myatm.entities.Withdraw;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Account account;
    private final List<String> amountsList = new ArrayList<>();
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private boolean onStartUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("SP", Context.MODE_PRIVATE);
        editor = sp.edit();
        onStartUp = true;

        account = new Account();
        initSpinner();
        changeBalanceView();

        onStartUp = false;
    }

    private void initSpinner() {
        final boolean[] onSpinnerStartUp = {true};
        if (amountsList.isEmpty()) {
            for (Integer in : account.getAmounts()) {
                amountsList.add(String.valueOf(in));
            }
        }

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, amountsList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);

        int defaultAmount = sp.getInt("defaultAmount", -1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (onSpinnerStartUp[0] && defaultAmount != -1) {
                    account.setCurrentAmount(Integer.valueOf(amountsList.get(defaultAmount)));
                    spinner.setSelection(defaultAmount);
                    onSpinnerStartUp[0] = false;
                } else {
                    account.setCurrentAmount(Integer.valueOf(amountsList.get(position)));
                }
                editor.remove("defaultAmount");
                editor.putInt("defaultAmount", position);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onDeposit(View view) {
        Deposit.deposit(account);
        changeBalanceView();
    }

    public void onWithdraw(View view) {
        Withdraw.withdraw(account);
        changeBalanceView();
    }

    private void changeBalanceView() {
        TextView balance = findViewById(R.id.balance_view);
        if (onStartUp && sp.getInt("balance", -1) != -1) {
            balance.setText(String.valueOf(sp.getInt("balance", -1)));
        } else {
            balance.setText(String.valueOf(account.getBalance()));
            editor.remove("balance");
            editor.putInt("balance", account.getBalance());
        }

        TextView moneyInHand = findViewById(R.id.inhandmoney_view);
        if (onStartUp && sp.getInt("moneyInHand", -1) != -1) {
            moneyInHand.setText(String.valueOf(sp.getInt("moneyInHand", -1)));
        } else {
            moneyInHand.setText(String.valueOf(account.getMoneyInHand()));
            editor.remove("moneyInHand");
            editor.putInt("moneyInHand", account.getMoneyInHand());
        }
        editor.commit();
    }
}