package com.mycompany.login;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {
    
    //Test 1: A message with 250 charcters is accepted
    @Test
    public void testValidMessageLength() {
        String text = "A".repeat(250);
        Message message = new Message(
                "1234567890",
                "+27718693002",
                text
        );
        
        assertEquals(
                 "Message ready to send.",
                 message.checkMessageLength()
        );
    }
    
    //Test 2: A message Longer than 250 characters is rejected
    @Test
    public void testInvalidMessageLength() {
        String text = "A".repeat(251);
        Message message = new Message(
                "1234567890",
                "+27718693002",
                text
        );
        
        assertEquals(
                "Message exceeds 250 characters by 1; please reduce the size.",
                message.checkMessageLength()
        );
    }
    
    //Test 3: A valid SouthAfrican cellphone number
    @Test
    public void testValidRecipientCell() {
        Message message = new Message(
                "1234567890",
                "+27718693002",
                "Hi Mike, see you tonight."
        );
        
        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell()
        );
    }
    
    //Test 4: An invalid cellphone number
    @Test
    public void testInvalidRecipientCell() {
        Message message = new Message(
                "1234567890",
                "08575975889",
                "Hi Keegan."
        );
        
        assertTrue(
                message.checkRecipientCell().startsWith(
                        "Cell phone number is incorrectly formatted"
                )
        );
    }
    
    //Test 5: Message ID must not exceed 10 characters
    @Test
    public void testValidMessageID() {
        Message message = new Message(
                "1234567890",
                "+27718693002",
                "Hello there."
        );
        
        assertTrue(message.checkMessageID());
    }
    
    //Test 6: Message hash contains the first and last words
    @Test
    public void testMessageHash() {
        Message message = new Message(
                "1234567890",
                "+27718693002",
                "Hi Mike, can you join us tonight?"
        );
        
        String hash = message.createMessageHash();
        
        assertTrue(hash.startsWith("12:"));
        assertTrue(hash.endsWith("HITONIGHT"));
        assertEquals(hash.toUpperCase(), hash);
    }
    
    //Test 7: Send a message
    @Test
    public void testSendMessage() {
        Message message = new Message(
                "1234567890",
                "+27718693002",
                "Hello there."
        );
        
        assertEquals(
                "Message successfully sent.",
                message.SentMessage(1)
        );
    }
    
    //Test 8: Disregard a message
    @Test
    public void testDisregardMessage() {
        Message message = new Message(
                "1234567890",
                "+27718693002",
                "Hello there."
        );
        
        assertEquals(
                "Press 0 to delete the message.",
                message.SentMessage(2)
        );
    }
    
    //Test 9: Store a message
    @Test
    public void testStoreMessage() {
        Message message = new Message(
                "123456789",
                "+27718693002",
                "Hello there."
        );
        
        assertEquals(
                "Message successfully stored.",
                message.SentMessage(3)
        );
    }
    
    //Test 10: Message deatils are displayed
    @Test
    public void testPrintMessages() {
        Message message = new Message(
                "1234567890",
                "+27718693002",
                "Hello there."
        );
        
        String details = message.printMessages();
        assertTrue(details.contains("1234567890"));
        assertTrue(details.contains("+27718693002"));
        assertTrue(details.contains("Hello there."));
    }  
}
