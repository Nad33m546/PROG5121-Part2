package com.mycompany.login;

public class Login {
    
    // Variables
    private final String username;
    private final String password;
    private final String cellPhoneNumber;
    private final String firstName;
    private final String lastName;
    
    //Constructor
    public Login(String username, String password, String cellPhoneNumber,
                 String firstName, String lastName) {
    
    this.username = username;
    this.password = password;
    this.cellPhoneNumber = cellPhoneNumber;
    this.firstName = firstName;
    this.lastName = lastName;
}

//Check if username is correctly formatted
public boolean checkUserName() {

    return username.contains("_") && username.length() <= 5;
}

//Check if password meets complexcity requirements
public boolean checkPasswordComplexity() {

    if (password.length() < 8) {
        return false;
}
boolean hasCapitalLetter = false;
boolean hasNumber = false;
boolean hasSpecialCharacter = false;

for (   var character : password.toCharArray()) {

    if (Character.isUpperCase(character)) {
       hasCapitalLetter = true;
}

    if (Character.isDigit(character)) {
        hasNumber = true;
    }

    if (!Character.isLetterOrDigit(character)) {
        hasSpecialCharacter = true;
    }
}

return hasCapitalLetter
        && hasNumber
        && hasSpecialCharacter;
}

//Check if cellphone number is correctly formatted
public boolean checkCellPhoneNumber() {

   String regex = "\\+27[0-9]{9}$";

   return cellPhoneNumber.matches(regex);
}

//register the user
public String registerUser() {

   if (!checkUserName()) {
       
       return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters long.";
   }

   if (!checkPasswordComplexity()) {
       
       return "password is not correctly formatted; please ensure that the password contains at least eight charcters, a capital letter, a number, a special character.";
   }

   if (!checkCellPhoneNumber()) {

       return "Cell phone number incorrectly formatted or does not contain internatonal code.";
   }

   return "Registration successful.";
}

// Check login details
public boolean loginUser(String enteredUsername,
                         String enteredPassword) {

    return enteredUsername.equals(username)
            && enteredPassword.equals(password);
}

// Return login status
public String returnLoginStatus(boolean loginSuccessful) {

    if (loginSuccessful) {

        return "Welcome " + firstName + " " + lastName
                + ", it is great to see you again.";

    } else {

        return "Username or password incorrect, please try again.";
    }
  }
}