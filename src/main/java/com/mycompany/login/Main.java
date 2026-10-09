package com.mycompany.login;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("==============================");
        System.out.println("           QUICKCHAT");
        System.out.println("==============================");
        System.out.println();
        
        // ===============================
        // REGISTRATION
        // ===============================
        
        System.out.println("create your account");
        System.out.println();
        
        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();
        
        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();
        
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        
        System.out.println("Enter South African cell phone number: ");
        String cellPhoneNumber = scanner.nextLine();
        
        Login login = new Login(
                username,
                password,
                cellPhoneNumber,
                firstName,
                lastName
        );
        
        System.out.println();
        
        // ==============================
        // REGISTRATION VALIDATION
        // ==============================
        
        if (login.checkUserName()) {
            
            System.out.println(
                   "Username is not correctly formatted; "
                   + "please ensure that your username contains "
                   + "an underscore and is not more then five "
                   + "charcters in length."
            );
            
            scanner.close();
            return;
        }
        
        if (login.checkPasswordComplexity()) {
            
            System.out.println(
                    "Password successfully captured."
            );
        } else {
            
            System.out.println(
                    "Password is not correctly formatted; "
                    + "please ensure that the password contains "
                    + "at least eight characters, a capital letter,"
                    + "a number, and a special character."
            );
            
            scanner.close();
            return;
        }
        
        if (login.checkCellPhoneNumber()) {
            
            System.out.println(
                    "Cell phone number successfully added.");
            
        } else {
            
            System.out.println(
                    "Cell phone number incorrectly formatted"
                    + "or does not contain international code."
            );
            
            scanner.close();
            return;
        }
        
        System.out.println();
        System.out.println("Registration successful.");
        
        //==============================
        //LOGIN
        //==============================
        
        System.out.println();
        System.out.println("=============================");
        System.out.println("               LOGIN");
        System.out.println("=============================");
        
        System.out.println("Enter username: ");
        String enteredUsername = scanner.nextLine();
        
        System.out.println("Enter password: ");
        String enteredPassword = scanner.nextLine();
        
        boolean loginSuccessful = 
                login.loginUser(
                        enteredUsername,
                        enteredPassword
                );
        
        System.out.println();
        
        System.out.println(
                login.returnLoginStatus(loginSuccessful)
        );
        
        // Do not continue if login failed
        if(!loginSuccessful) {
            
            scanner.close();
            return;
        }
        
        // ==============================
        // QUICKCHAT
        // ==============================
        
        System.out.println();
        System.out.println("Welcome to Quickchat.");
        
        boolean running = true;
        
        int totalMessages = 0;
        
        while (running) {
            
            System.out.println();
            System.out.println("==============================");
            System.out.println("           QUICKCHAT MENU");
            System.out.println("==============================");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.print("3. Quit");
            
            System.out.println("Choose an option: ");
            
            String option = scanner.nextLine();
            
            switch (option) {
                
                // ==============================
                // SEND MESSAGES
                // ==============================
                
                case "1":
                    
                    System.out.println(
                            "How many messages would you like to send? "
                    );
                    
                    int numberOfMessages;
                    
                    try {
                        
                        numberOfMessages = 
                                Integer.parseInt(
                                        scanner.nextLine()
                                );
                    } catch (NumberFormatException e) {
                        
                        System.out.println(
                                "Please enter a valid number."
                        );
                        
                        break;
                    }
                    
                    for (int i = 1;
                         i <= numberOfMessages;
                         i++) {
                    
                    System.out.println();
                    System.out.println(
                            "========== MESSAGE "
                            + i 
                            + "=========="
                    );
                    
                    System.out.print(
                            "Enter recipient cell number: "
                    );
                    
                    String recipient = 
                            scanner.nextLine();
                    
                    System.out.print(
                            "Enter your message: "
                    );
                    
                    String messageText = 
                            scanner.nextLine();
                    
                    Message message =
                            new Message(
                                    recipient,
                                    messageText
                            );
                    
                    System.out.println();
                    
                    // Check recipient
                    String recipientResult = 
                            message.checkRecipientCell();
                    
                    System.out.println(
                            recipientResult
                    );
                    
                    // Check message length
                    String messageResult =
                            message.checkMessageLength();
                    
                    System.out.println(
                            messageResult
                    );
                    
                    // Stop if recipient is invalid
                    if (!recipientResult.equals(
                            "Cell phone number successfully captured."
                    )) {
                        
                        System.out.println(
                                "Message was not sent."
                        );
                        
                        continue;
                    }
                    
                    // Stop if message is too long
                    if (!messageResult.equals(
                            "Message ready to send."
                    )) {
                        
                        System.out.println(
                                "Message was not sent."
                        );
                        
                        continue;
                    }
                    
                    System.out.println();
                    
                    // Display hash
                    System.out.println(
                            "Message Hash: "
                            + message.createMessageHash()
                    );
                    
                    System.out.println();
                    
                    // Message options
                    System.out.println(
                            "1. Send Message"
                    );
                    
                    System.out.println(
                            "2. Disregard Message"
                    );
                    
                    System.out.println(
                            "3. Store Message to send later"
                    );
                    
                    System.out.print(
                            "Choose an option: "
                    );
                    
                    int messageChoice;
                    
                    try {
                        
                        messageChoice = 
                                Integer.parseInt(
                                        scanner.nextLine()
                                );
                    
                    } catch (NumberFormatException e) {
                        
                        System.out.println(
                                "invalid option."
                        );
                        
                        continue;
                    }
                    
                    String result =
                            message.SentMessage(
                                    messageChoice
                            );
                    
                    System.out.println();
                    System.out.println(result);
                    
                    // Count sent messages
                    if (messageChoice == 1) {
                        
                        totalMessages++;
                    }
                    
                    // Display message details
                    System.out.println();
                    System.out.println(
                            "========== MESSAGE DETAILS =========="
                    );
                    
                    System.out.println(
                            message.printMessages()
                    );
                }
                         
                break;
                
                // ==============================
                // QUIT
                // ==============================
                
                case "3":
                    
                    running = false;
                    
                    System.out.println();
                    System.out.println(
                            "Thank you for using QuickChat,"
                    );
                    
                    break;
                    
                    // ==============================
                    // INVALID OPTION
                    // ==============================
                    
                default:
                    
                    System.out.println(
                            "Invalid option."
                            +  "Please choose 1, 2 or 3. "
                    );
            }
        }
        
        // ==============================
        // TOTAL MESSAGES
        // ==============================
        
        System.out.println();
        System.out.println(
                "Total messages sent: "
                + totalMessages
        );
        
        scanner.close();
    }
 }