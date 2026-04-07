package bank;

import java.io.File;
import java.util.LinkedList;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<Account> accounts = new LinkedList<>();
        String file = "Accounts.csv";

        File csv = new File(file);
        if (!csv.exists()) {
            loadSampleAccounts(accounts);
        } else {
            try {
                ReadAccounts reader = new ReadAccounts(file);
                LinkedList<String> fn = reader.getFirstNames();
                LinkedList<String> ln = reader.getLastNames();
                LinkedList<Integer> accNums = reader.getAccounts();
                LinkedList<Integer> bals = reader.getBalances();

                for (int i = 0; i < fn.size(); i++) {
                    accounts.add(new Account(fn.get(i), ln.get(i), accNums.get(i), bals.get(i)));
                }
            } catch (Exception e) {
                System.out.println("Error loading file. Using sample accounts.");
                loadSampleAccounts(accounts);
            }
        }

        SwingUtilities.invokeLater(() -> new GUI(accounts));
    }

    private static void loadSampleAccounts(LinkedList<Account> accounts) {
        accounts.add(new Account("Dewmi", "Umegha", 1001, 5000));
        accounts.add(new Account("Alex", "Green", 1002, 3000));
        accounts.add(new Account("Nina", "Brown", 1003, 7000));
        System.out.println("Loaded default sample accounts.");
    }
}
