package com.devpro.designpattern;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.devpro.designpattern.AbstractFactoryPattern.Chair;
import com.devpro.designpattern.AbstractFactoryPattern.FurnitureAbstractFactory;
import com.devpro.designpattern.AbstractFactoryPattern.FurnitureFactory;
import com.devpro.designpattern.AbstractFactoryPattern.MaterialType;
import com.devpro.designpattern.AbstractFactoryPattern.Table;
import com.devpro.designpattern.AdapterPattern.JapaneseAdaptee;
import com.devpro.designpattern.AdapterPattern.TranslatorAdapter;
import com.devpro.designpattern.AdapterPattern.VietnameseTarget;
import com.devpro.designpattern.BuilderPattern.FastFoodOrderBuilder;
import com.devpro.designpattern.BuilderPattern.Order;
import com.devpro.designpattern.BuilderPattern.type.BreadType;
import com.devpro.designpattern.BuilderPattern.type.OrderType;
import com.devpro.designpattern.BuilderPattern.type.SauceType;
import com.devpro.designpattern.ChainOfResponsibilityPattern.LeaveRequest;
import com.devpro.designpattern.ChainOfResponsibilityPattern.LeaveRequestWorkFlow;
import com.devpro.designpattern.FactoryPattern.Bank;
import com.devpro.designpattern.FactoryPattern.BankFactory;
import com.devpro.designpattern.FactoryPattern.BankType;
import com.devpro.designpattern.ObserverPattern.AccountService;
import com.devpro.designpattern.ObserverPattern.Logger;
import com.devpro.designpattern.ObserverPattern.LoginStatus;
import com.devpro.designpattern.ObserverPattern.Mailer;
import com.devpro.designpattern.ObserverPattern.Protector;
import com.devpro.designpattern.SingletonPattern.BillPughSingleton;
import com.devpro.designpattern.SingletonPattern.DoubleCheckLockingSingleton;
import com.devpro.designpattern.SingletonPattern.EagerInitializedSingleton;
import com.devpro.designpattern.SingletonPattern.LazyInitializedSingleton;
import com.devpro.designpattern.SingletonPattern.StaticBlockSingleton;
import com.devpro.designpattern.SingletonPattern.ThreadSafeLazyInitializedSingleton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Singleton Pattern
        singletonPattern();

        //Factory Pattern
        factoryPattern();

        //Abstract Factory Pattern
        abstractFactoryPattern();

        //Builder Pattern
        builderPattern();

        //Adapter Pattern
        adapterPattern();

        //Chain of Responsibility Pattern
        chainOfResponsibilityPattern();

        //Observer Pattern
        observerPattern();
    }



    void observerPattern() {
        AccountService account1 = createAccount("contact@gpcoder.com", "127.0.0.1");
        account1.login();
        account1.changeStatus(LoginStatus.EXPIRED);

        System.out.println("---");
        AccountService account2 = createAccount("contact@gpcoder.com", "116.108.77.231");
        account2.login();
    }

    private static AccountService createAccount(String email, String ip) {
        AccountService account = new AccountService(email, ip);
        account.attach(new Logger());
        account.attach(new Mailer());
        account.attach(new Protector());
        return account;
    }

    void chainOfResponsibilityPattern() {
        LeaveRequestWorkFlow.getApprover().approveLeave(new LeaveRequest(2));
        System.out.println("---");
        LeaveRequestWorkFlow.getApprover().approveLeave(new LeaveRequest(5));
        System.out.println("---");
        LeaveRequestWorkFlow.getApprover().approveLeave(new LeaveRequest(7));

    }

    void adapterPattern() {
        VietnameseTarget client = new TranslatorAdapter(new JapaneseAdaptee());
        client.send("Xin chào");
    }

    void builderPattern() {
        Order order = new FastFoodOrderBuilder()
                .orderType(OrderType.ON_SITE).orderBread(BreadType.OMELETTE)
                .orderSauce(SauceType.SOY_SAUCE).build();
        System.out.println(order);
    }

    void abstractFactoryPattern() {
        FurnitureAbstractFactory factory = FurnitureFactory.getFactory(MaterialType.FLASTIC);

        Chair chair = factory.createChair();
        chair.create(); // Create plastic chair

        Table table = factory.createTable();
        table.create(); // Create plastic table
    }

    void  factoryPattern() {
        Bank bank = BankFactory.getBank(BankType.TPBANK);
        System.out.println(bank.getBankName());
    }

    void  singletonPattern() {
        EagerInitializedSingleton eagerInitializedSingleton = EagerInitializedSingleton.getInstance();
        Log.d("DP", eagerInitializedSingleton.hashCode() + "");

        EagerInitializedSingleton eagerInitializedSingleton1 = EagerInitializedSingleton.getInstance();
        Log.d("DP", eagerInitializedSingleton1.hashCode() + "");

        StaticBlockSingleton staticBlockSingleton = StaticBlockSingleton.getInstance();
        Log.d("DP", staticBlockSingleton.hashCode() + "");

        StaticBlockSingleton staticBlockSingleton1 = StaticBlockSingleton.getInstance();
        Log.d("DP", staticBlockSingleton1.hashCode() + "");

        LazyInitializedSingleton lazyInitializedSingleton = LazyInitializedSingleton.getInstance();
        Log.d("DP3", lazyInitializedSingleton.hashCode() + "");

        LazyInitializedSingleton lazyInitializedSingleton1 = LazyInitializedSingleton.getInstance();
        Log.d("DP3", lazyInitializedSingleton1.hashCode() + "");

        ThreadSafeLazyInitializedSingleton threadSafeLazyInitializedSingleton = ThreadSafeLazyInitializedSingleton.getInstance();
        Log.d("DP4", threadSafeLazyInitializedSingleton.hashCode() + "");

        ThreadSafeLazyInitializedSingleton threadSafeLazyInitializedSingleton1 = ThreadSafeLazyInitializedSingleton.getInstance();
        Log.d("DP4", threadSafeLazyInitializedSingleton1.hashCode() + "");

        DoubleCheckLockingSingleton doubleCheckLockingSingleton = DoubleCheckLockingSingleton.getInstance();
        Log.d("DP5", doubleCheckLockingSingleton.hashCode() + "");

        DoubleCheckLockingSingleton doubleCheckLockingSingleton1 = DoubleCheckLockingSingleton.getInstance();
        Log.d("DP5", doubleCheckLockingSingleton1.hashCode() + "");

        BillPughSingleton billPughSingleton = BillPughSingleton.getInstance();
        Log.d("DP6", billPughSingleton.hashCode() + "");

        BillPughSingleton billPughSingleton1 = BillPughSingleton.getInstance();
        Log.d("DP6", billPughSingleton1.hashCode() + "");
    }
}