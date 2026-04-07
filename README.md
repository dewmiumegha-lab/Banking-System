🏦 Java Banking System

A fully functional desktop Banking Management System built with Java and Java Swing GUI. This application enables users to manage bank accounts with real-time deposit, withdrawal, and fund transfer operations — all backed by persistent CSV-based data storage.

📸 Preview

A desktop GUI window displaying account holders, their account numbers and balances, with action buttons for Deposit, Withdraw, and Transfer operations.


✨ Features

📋 View All Accounts — Display all account holders with names, account numbers, and balances
💰 Deposit — Add funds to any account by account number
💸 Withdraw — Deduct funds from any account
🔁 Transfer — Transfer funds between two accounts instantly
🔄 Live Refresh — UI updates in real-time after every transaction
📂 CSV File Loading — Reads account data from Accounts.csv on startup
🛡️ Error Handling — Graceful fallback to sample accounts if CSV is missing or malformed


📄 CSV Format
The Accounts.csv file should follow this format (no header row):
FirstName,LastName,AccountNumber,Balance
Example:
csvDewmi,Umegha,11,10000
Udara,Nethmi,22,10
Ginuka,Weragoda,33,50000
Sanduni,Priyalakshi,44,99999

🚀 Getting Started
Prerequisites

Java JDK 8 or higher
Any Java IDE (IntelliJ IDEA, Eclipse, NetBeans) or command line

Running the Project
Option 1 — IDE

Clone or download the project
Open in your Java IDE
Place Accounts.csv in the project root directory
Run Main.java

Option 2 — Command Line
bash# Compile all files
javac bank/*.java

# Run the application
java bank.Main

⚠️ Make sure Accounts.csv is in the same directory where you run the command, or the app will fall back to built-in sample accounts.


🔧 How It Works
1. Startup (Main.java)

Checks if Accounts.csv exists in the current directory
If found, uses ReadAccounts to parse and load account data
If not found or invalid, loads 3 default sample accounts
Launches the GUI on the Swing Event Dispatch Thread

2. Data Reading (ReadAccounts.java)

Opens the CSV file 4 times — once for each field (first name, last name, account number, balance)
Parses each row using comma splitting
Returns separate LinkedList collections for each field

3. Account Management (Account.java + Customer.java)

Customer is the base class holding name details
Account extends Customer, adding balance and account number
deposit() and withdraw() directly modify the balance

4. Transactions (Transaction.java)

The transfer() method calls withdraw() on the source account and deposit() on the destination
Simple and clean single-responsibility design

5. GUI (GUI.java)

Built with Java Swing using JFrame, JButton, JTextField, JScrollPane
An inner HandlerClass implements ActionListener to handle all button events
Display refreshes automatically after every operation


📌 OOP Concepts Used
ConceptWhere AppliedInheritanceAccount extends CustomerEncapsulationPrivate fields with getters/setters in Customer and AccountAbstractionTransaction abstracts transfer logic away from the GUIEvent-Driven ProgrammingHandlerClass inner class with ActionListenerFile I/OReadAccounts uses BufferedReader and FileReaderCollectionsLinkedList<Account> used throughout

🛠️ Possible Improvements

 Save updated balances back to the CSV file after transactions
 Prevent overdraft (negative balance check in withdraw())
 Add account creation and deletion from the GUI
 Use a single-pass CSV reader instead of reading the file 4 times
 Add a transaction history log
 Switch to a database (SQLite/MySQL) for persistent storage
 Add input validation and user-friendly error messages


👩‍💻 Author
Dewmi Umegha
Java Developer | OOP Enthusiast
