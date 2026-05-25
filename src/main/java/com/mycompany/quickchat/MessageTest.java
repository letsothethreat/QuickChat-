/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Student
 */
public class MessageTest {
    
   //Test 1: Message length - succcess (under 250)
    @Test
    public void testCheckMessageLengthSuccess() {
        String result = Messages.checkMessageLength("Hey Kokeletso, do you want to go out tonight?");
        assertEquals("Message ready to send.", result);
    }
   
   //Test 2: Message length - unsuccessful (over 250)
    @Test
    public void testCheckMessageLengthFailure() {
        String longMessage = "A".repeat(251);
        String result = Messages.checkMessageLength(longMessage);
        assertTrue(result.contains("Message exceeds 250 characters by"));
    }
    
    //Test 3: Recipient cell - success(valid +27 number)
    @Test
    public void testCheckRecipientCellphoneFailure() {
        Messages msg = new Messages("0675870987",
                "Hi Lihle, are you home safe?");
                String result = msg.checkRecipientCellphone();
                assertTrue(result.contains("Incorrectly written")||
                        result.contains("international code"));
        
    }
    
    // Test 4: Message hash correct format (all caps, contains colons)
    @Test
    public void testCreateMessageHash() {
        Messages msg = new Messages("+27849874657",
            "Hi Kokeletso, can you join us for dinner tonight?");
        String hash = msg.getMessageHash();
        assertNotNull(hash);
        assertTrue(hash.contains(":"));
        assertEquals(hash, hash.toUpperCase());
    }
    
    //Test 5: SentMessage - disregard
    @Test
    public void testSentMessageSend() {
        Messages msg = new Messages("+27849874657",
             "Hi Kokeletso, can you join us for dinner?");
        assertEquals("Message sent successfully.", msg.SentMessage(1));
       
    }
    
    //Test 6: SentMessage - disregard
    @Test
    public void testSentMessageDisregard() {
        Messages msg = new Messages("+27849874657",
             "Hi Kokeletso, can you join us for dinner tonight?");
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
    }
    
    //Test 7: Return total messages sent
    @Test
    public void testReturnTotalMessages() {
        int before = Messages.returnTotalMessages();
        Messages msg = new Messages("27849874657",
             "Hi Kokeletso, can you join us for dinner?");
        msg.SentMessage(1);
        assertEquals(before + 1, Messages.returnTotalMessages());
    }
    
    //Test 8: Message ID not more than 10 characters
    @Test
    public void testCheckMessageID() {
        Messages msg = new Messages("27849874657",
             "Hi Kokeletso, can you join us for dinner tonight?");
        assertTrue(msg.checkMessageID());
    }
}
    