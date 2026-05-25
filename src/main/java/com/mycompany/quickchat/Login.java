/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author Student
 */
public class Login {
    private static final String USER_FILE = "user.txt";
    
    //Validation methods
    public boolean isValidUsername(String username) {
        return username.contains("_") && username.length() <= 5;
        
    }
    public boolean isValidPassword(String Password) {
        return Password.length() >= 8
                && Password.matches(".*[A-Z].*")
                && Password.matches(".*[0-9].*")
                && Password.matches(".*[!@#$%^*()_+\\[\\]{};':\"\\[,.<>/?].*");
        
    }
    public boolean isValidCellphone(String CellphoneNumber){
        //must start with +27, followed by 9 digits (12 in total)
        return CellphoneNumber.matches("\\+27[0-9]{9}");
        
    }
    
    //Register @
    public void registerUser() {
        Scanner input = new Scanner(System.in);
        
    System.out.println("Enter Username: ");
    String username = input.nextLine();
    
    System.out.println("Enter Password: ");
    String Password = input.nextLine();
    
    System.out.println("Enter Cellphone (+27...): ");
    String CellphoneNumber = input.nextLine();
    
    String message = "";
    boolean allValid = true;
    
        //Username
        message+= "\nUSERNAME CHECK:\n";
        if (username.contains("_")){
            message += "Contains an underscore (_)\n";
         
        }else {
            message += "Must contain an underscore (_)\n";
            allValid = false;
        }
        if (username.length() <= 5) {
            message += "[SUCCESSFUL] Length is 5 or less\n";
            
        }else {
            message += "[UNSUCCESSFUL] Must not be more than 5 characters\n";
            allValid = false;
            
        }
        //password
        message += "\nPASSWORD CHECK\n";
        if (Password.length() >=8) {
            message += "[SUCCESSFUL] Password is at least 8 characters long\n";
        }else {
            message += "[UNSUCCESSFUL] Password must be 8 characters long\n";
            allValid = false;
        }
        if(Password.matches(".*[A-Z].*")) {
            message += "[SUCCESSFUL] contains uppercase letter\n";
            
        }else {
            message += "[UNSUCCESSFUL] Must contain at least one uppercase letter\n";
            allValid = false;
        }
        if (Password.matches(".*[!@#$%^*()_+\\[\\]{};':\"\\[,.<>/?].*")) {
            message += "[SUCCESSFUL] contains a special character\n";
        }else {
            message += "[UNSUCCESSFUL] must contain at least one special character (!@~$%...)\n";
            allValid = false;
        }
        //Cellphone
        message += "\nCELLPHONE CHECK:\n";
        if (isValidCellphone(CellphoneNumber)) {
            message += "[SUCCESSFUL] Valid South African number (+27 + 9 digits)\n";
            
        }else {
            message += "[unsuccessful] must be +27 followed by exactly 9 didgits (e.g. +27814356789\n";
            allValid = false;
        }
        message += "\n************************\n";
        if (allValid) {
            //Checks if username exist
        if (userExist(username)) {
            message += "REGISTRATION UNSUCCESSFUL. Username '" + username + "' already exist.\n";
        } else {
            SaveUser(username, Password, CellphoneNumber);
            message += "REGISTRATION SUCCESSFUL! Welcome," + username + "!\n";
        }
        }else {
            message += "REGISTRATION FAILED. Please fix the errors above. \n";
               }
        
        message += "*************************\n";
            System.out.println(message);
        }
        //Checks for saved users
        public boolean loginUser() {
            Scanner input = new Scanner(System.in);
            System.out.println("\n*** Login ***");
            
            System.out.println("Enter Username: ");
            String username = input.nextLine().trim();
            
            System.out.println("Enter password: ");
            String Password = input.nextLine().trim();
            
            System.out.println("*************************\n");
        if (checkCredentials(username, Password)) {
            System.out.println("LOGIN SUCCESSFUL! Welcome back," + username + "!");
            System.out.println("************************\n");
            return true;
        }else {
            System.out.println("LOGIN UNSUCCESSFUL> Incorrect username or password.");
            System.out.println("************************\n");
            return false;
        }
        
        }
        //File helpers
        /** Saves new users to the System */
        private void SaveUser(String username, String Password, String CellphoneNumber) {
            try(FileWriter fw = new FileWriter(USER_FILE, true);
                    
                    BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(username + "," + Password + "," + CellphoneNumber);
                
                bw.newLine();
            }catch (IOException e) {
                System.out.println("[ERROR] Could not find user: " + e.getMessage());
            }
            }
        /**Checks if user already Exist in the file */
        private boolean userExist(String username) {
            try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
            if(parts.length >= 1 && parts[0].equals(username)) {
                
            return true;
            }
                }
            } catch (IOException e) {
                
            }
            return false;
        }
        /**Compares username + password with already existing users*/
        private boolean checkCredentials(String username, String Password) {
            try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))){
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2
                            && parts[0].equals(username)
                            && parts[1].equals(Password)) {
                        return true;
                    }    
                }
            } catch(IOException e) {
                System.out.println("[ERROR] Could not read user file: " + e.getMessage());
            }
            return false;
        }

    
}

