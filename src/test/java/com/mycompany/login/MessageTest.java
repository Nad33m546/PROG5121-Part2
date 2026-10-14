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
    public void testValidREcipientCell() {
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
    
}
