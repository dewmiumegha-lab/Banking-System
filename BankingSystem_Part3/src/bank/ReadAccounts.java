package bank;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ReadAccounts {
    private String URL;
    public ReadAccounts(String URL) {
        this.URL = URL;
    }
    public LinkedList<String> getFirstNames() {
        LinkedList<String> firstNames = new LinkedList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(URL))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                firstNames.add(values[0]);
            }
        } catch (IOException e) {
            System.out.println("Error reading first names from file: " + e.getMessage());
            System.out.println("Make sure 'Accounts.csv' is in the same directory as your Java program.");
        }
        
        return firstNames;
    }
    public LinkedList<String> getLastNames() {
        LinkedList<String> lastNames = new LinkedList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(URL))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                lastNames.add(values[1]);
            }
        } catch (IOException e) {
            System.out.println("Error reading last names from file: " + e.getMessage());
            System.out.println("Make sure 'Accounts.csv' is in the same directory as your Java program.");
        }
        
        return lastNames;
    }
    public LinkedList<Integer> getAccounts() {
        LinkedList<Integer> accounts = new LinkedList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(URL))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                accounts.add(Integer.parseInt(values[2]));
            }
        } catch (IOException e) {
            System.out.println("Error reading account numbers from file: " + e.getMessage());
            System.out.println("Make sure 'Accounts.csv' is in the same directory as your Java program.");
        } catch (NumberFormatException e) {
            System.out.println("Error parsing account number: " + e.getMessage());
            System.out.println("Make sure account numbers in the CSV are valid integers.");
        }
        
        return accounts;
    }
    public LinkedList<Integer> getBalances() {
        LinkedList<Integer> balances = new LinkedList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(URL))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                balances.add(Integer.parseInt(values[3]));
            }
        } catch (IOException e) {
            System.out.println("Error reading balances from file: " + e.getMessage());
            System.out.println("Make sure 'Accounts.csv' is in the same directory as your Java program.");
        } catch (NumberFormatException e) {
            System.out.println("Error parsing balance value: " + e.getMessage());
            System.out.println("Make sure account balances in the CSV are valid integers.");
        }
        
        return balances;
    }
}