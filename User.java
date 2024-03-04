public class User {
    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    User(String userID, String userPIN, double accountBalance) {
        this.userID = userID;
        UserPIN = userPIN;
        this.accountBalance = accountBalance;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserPIN() {
        return UserPIN;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    String userID;
    String UserPIN;
    double accountBalance;
}
