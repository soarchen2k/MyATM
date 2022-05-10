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
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("SP", Context.MODE_PRIVATE);
        editor = sp.edit();

        account = new Account();
        initSpinner(sp);
        changeBalanceView();
    }

    private void initSpinner(SharedPreferences sp) {
        final boolean[] onStart = {true};
        if (amountsList.isEmpty()) {
            for (Integer in : account.getAmounts()) {
                amountsList.add(String.valueOf(in));
            }
        }

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, amountsList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);
        String string = sp.getString("test", "Empty");
        System.out.println(string);
        int defaultAmount = sp.getInt("defaultAmount", -1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (onStart[0] && defaultAmount != -1) {
                    account.setCurrentAmount(Integer.valueOf(amountsList.get(defaultAmount)));
                    onStart[0] = false;
                } else {
                    account.setCurrentAmount(Integer.valueOf(amountsList.get(position)));
                }

                editor.clear();
                editor.putInt("defaultAmount", position);
                editor.putString("test", "TEST2");
                boolean commit = editor.commit();
                System.out.println(commit);
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
        balance.setText(String.valueOf(account.getBalance()));
        TextView moneyInHand = findViewById(R.id.inhandmoney_view);
        moneyInHand.setText(String.valueOf(account.getMoneyInHand()));
    }
}