/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;
import java.util.Scanner;
/**
 *
 * @author Student
 */
public class QuickChat {

    public static void main(String[] args) {
        
        Login login = new Login();
        Scanner input = new Scanner(System.in);
        
        
        System.out.println("*****QuickChat*****");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("Choose an option(1 or 2):");
        
        String choice = input.nextLine();
        boolean loggedIn = false;
        if (choice.equals("1")) {
            login.registerUser();
        } else if (choice.equals("2")) {
            loggedIn = login.loginUser();
        }else {
            System.out.println("Invalid option, please restart and choose 1 or 2.");
            return;
                    
        }
        
        if (!loggedIn) {
            System.out.println("You must be logged in to send messages!");
            return;
            
        }
        System.out.println("******\nWelcome to QuickChat.*****");
        System.out.println("How many messages would you like to send?");
        int numMessages = Integer.parseInt(input.nextLine().trim());
        
        int count = 0;
        while (true) {
            System.out.println("\n1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("Choose an option: ");
            String menuChoice = input.nextLine().trim();
            
            if (menuChoice.equals("1")) {
                if (count >= numMessages) {
                    System.out.println("You have reached your message limit!");
                    continue;
                }
                
                System.out.println("Enter recipient (+27. . .): ");
                String recipient = input.nextLine().trim();
                
                System.out.println("Enter message: ");
                String messageText = input.nextLine().trim();
                
                //Check message length
                String lengthCheck = Messages.checkMessageLength(messageText);
                System.out.println(lengthCheck);
                if (messageText.length() > 250) continue;
                
                Messages msg = new Messages(recipient, messageText);
                
                //Check recipient
                System.out.println(msg.checkRecipientCellphone());
                
                System.out.println("\n1) Send Message");
                System.out.println("2) Disregard Message");
                System.out.println("3) Store Message");
                System.out.println("Choose an option: ");
                
            }
        }
    }
}
