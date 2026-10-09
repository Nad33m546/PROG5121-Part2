package com.mycompany.login;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Message {
    
    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    private static int messageNumber = 0;
    private static int totalMessages = 0;
    
    //Constructor
    public Message(String recipient, String messageText) {
        
        this.recipient = recipient;
        this.messageText = messageText;
        
        messageNumber++;
        
        generteMessageID();
        createMessageHash();
    }
    
    //Additional constructor for testing
    public Message(String messageID, String recipient, String messageText) {
        
        this.messageID = messageID;
        this.recipient = recipient;
        this.messageText = messageText;
        
        messageNumber++;
        
        createMessageHash();
    }
    
    //Generate a random 10-digit Message ID
    private void generteMessageID() {
        
        Random random = new Random();
        
        long number = 1000000000L
                + random.nextLong(9000000000L);
        
        messageID = String.valueOf(number);
    }
    
    //Check Message ID
    public boolean checkMessageID() {
        
        return messageID.length() <=10;
    }
    
    //Check recipient cellphone number
    public String checkRecipientCell(){
        
        if (recipient.matches("^\\+27[0-9]{9}$")) {
            return "Cell phone number successfully captured.";
          
        } else {
            
            return "Cell phone number is incorrectly formatted or does not contain internation code. Please correct the number and try again.";
        }
    }
    
    //Check message length
    public String checkMessageLength() {
        
        if (messageText.length() <=250) {
            
            return "Message ready to send.";
            
        } else {
            
            int charactersOver = messageText.length() - 250;
            
            return "Message exceeds 250 characters by "
                    + charactersOver
                    +"; please reduce the size.";
       }
    }
    
    //Create message hash
    public String createMessageHash() {
        
        if (messageText == null || messageText.trim().isEmpty()) {
            
            messageHash = messageID.substring(0, 2)
                    + ":"
                    + (messageNumber - 1)
                    + ":";
            
            return messageHash;
        }
        
        String[] words = messageText.trim().split("\\s+");
        
        String firstWord = words[0]
                .replaceAll("[^a-zA-z0-9]", "");
        
        String lastWord = words[words.length - 1]
                .replaceAll("[^a-zA-z0-9]", "");
        
        messageHash = messageID.substring(0, 2)
                + ":"
                + (messageNumber - 1)
                + ":"
                + firstWord
                + lastWord;
        
        messageHash = messageHash.toUpperCase();
        
        return messageHash;
    }
    
    //Send, disregard or store message
    public String SentMessage(int choice) {
        
        switch (choice) {
            
            case 1 -> {
                totalMessages++;
                
                return "Message successfully sent.";
            }
                
            case 2 -> {
                return "Press 0 to delete the message.";
            }
                
            case 3 -> {
                storeMessage();
                
                return "Message successfully stored.";
            }
                
            default -> {
                return "Invalid option.";
            }
        } 
    }
    
    //Store message in JSON file
    public void storeMessage() {
        
        String fileName = "stored_messages.json";
        
        try (FileWriter writer = new FileWriter(fileName, true)) {
            
            writer.write("{\n");
            writer.write("  \"messageID\": \"" + messageID + "\",\n");
            writer.write("  \"messageHash\": \"" + messageHash + "\",\n");
            writer.write("  \"recipient\": \"" + recipient + "\",\n");
            writer.write("  \"message\": \"" + escapeJson(messageText) + "\"\n");
            writer.write("}\n");
            
        } catch (IOException e) {
            
            System.out.println(
                     "Error storing message: " + e.getMessage()
            );
        }
    }
    
    //Escape quotation marks for JSON
    private String escapeJson(String text) {
        
        return text.replace("\\", "\\\\")
                .replace ("\"", "\\\"");
    }
    
    //Print message details
    public String printMessages() {
        
        return "MessageID: " + messageID
                +"\nMessage Hash: " + messageHash
                +"\nRecipient: " + recipient
                +"\nMessage: " + messageText;
    }
    
    //Return total messages sent
    public int returnTotalMessages() {
        
        return totalMessages;
    }
    
    //Getters
    public String getMessageID() {
        
        return messageID;
    }
    
    public String getRecipient() {
        
        return recipient;
    }
    
    public String getMessageText() {
        
        return messageText;
    }
    
    public String getMessageHash() {
        
        return messageHash;
    }
}
