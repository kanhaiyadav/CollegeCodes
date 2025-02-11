#include <iostream>
#include <map>
using namespace std;

class AccountNotFoundException : public exception {
public:
    const char* what() const throw() {
        return "Account does not exist!";
    }
};

class MinimumBalanceException : public exception {
public:
    const char* what() const throw() {
        return "Minimum balance of Rs. 3000 must be maintained!";
    }
};

class Account {
    string name, address, type;
    double balance;
public:
    Account() {}  // Default constructor for map usage
    Account(string n, string a, string t, double b) : name(n), address(a), type(t), balance(b) {}

    void deposit(double amount) { balance += amount; }

    void withdraw(double amount) {
        if (balance - amount < 3000) throw MinimumBalanceException();
        balance -= amount;
    }

    void display() {
        cout << "Name: " << name << "\nAddress: " << address << "\nType: " << type << "\nBalance: " << balance << endl;
    }
};

int main() {
    map<int, Account> accounts;
    accounts[101] = Account("Alice", "123 Street", "Savings", 5000);
    accounts[102] = Account("Bob", "456 Avenue", "Current", 7000);

    int acc_no, choice;
    double amount;

    try {
        cout << "Enter account number: ";
        cin >> acc_no;

        if (accounts.find(acc_no) == accounts.end()) {
            throw AccountNotFoundException();
        }

        cout << "1. Deposit\n2. Withdraw\nEnter choice: ";
        cin >> choice;

        if (choice == 1) {
            cout << "Enter amount to deposit: ";
            cin >> amount;
            accounts[acc_no].deposit(amount);
        } else if (choice == 2) {
            cout << "Enter amount to withdraw: ";
            cin >> amount;
            accounts[acc_no].withdraw(amount);
        } else {
            cout << "Invalid choice!" << endl;
        }

        accounts[acc_no].display();

    } catch (exception &e) {
        cout << "Exception: " << e.what() << endl;
    }

    return 0;
}
