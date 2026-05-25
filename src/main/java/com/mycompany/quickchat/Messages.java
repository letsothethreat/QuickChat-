/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
/*
 *
 * @author Student
 */
public class Messages {
    
    private String messageID;
    private int numMessagesSent;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String sentStatus;
    
    private static ArrayList<Messages>  sentMessages = new ArrayList<>();
    private static int totalMessagesSent = 0;
    private static int messageCounter = 0;
    
    //Constructor
    public Messages(String recipient, String messageText) {
        this.messageID = generateMessageID();
        Messages.messageCounter++;
        this.numMessagesSent = Messages.messageCounter;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }
    
    //Getters
    public String getMessageID() { return messageID; }
    public int getNameMessagesSent() {return numMessagesSent; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
    public String getSentStatus() { return sentStatus; }
    
    //Generate a random 10-digit message ID
    private String generateMessageID() {
        Random rand = new Random();
        long id = (long) (rand.nextDouble() * 6_000_000_000L) + 4_000_000_000L;
        return String.valueOf(id);
    }
    //Boolean: checkMessageID() makes sure message ID is not more than 10 characters
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }
    //String: CheckRecipientCellphone() makes sure that cellphone number is 10 characters and starts with '+'
    public String checkRecipientCellphone() {
        if (recipient.length() <= 12 && recipient.startsWith("+27")) {
            return "Cellphone numbermsuccessfully captured.";
        } else {
            return "Cellphone number is incorrectly written or does not contain a South African"
                    + "international code. Please correct the number and try again.";
        }
    }
    
    //String: createMessageHash() -  firstTwoOfID:messageNumber:FIRSTWORDLASTWORD (all caps)
    private String createMessageHash() {
        String firstTwo = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^A-Za-z0-9]", "").toUpperCase();
        String lastWord = words[words.length - 1].replaceAll("A-Za-z0-9", "").toUpperCase();
        return (firstTwo + ":" + numMessagesSent + ":" + firstWord + lastWord).toUpperCase();
    }
    
}
