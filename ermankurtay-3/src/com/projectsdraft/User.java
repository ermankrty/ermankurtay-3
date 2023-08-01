package com.projectsdraft;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class User {
	
    private String username;
    private String password;
    private String name;
    private int loginAttempts;

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.loginAttempts = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public boolean isLocked() {
        return loginAttempts >= 5;
    }

    public void increaseLoginAttempts() {
        loginAttempts++;
    }

    public static void main(String[] args) throws IOException {
    	
        String line = "";
        
        Scanner scanner = new Scanner(System.in);
        
        int numLines = 4;
        
        String usernameInput;
        String passwordInput;

        User users[] = new User[numLines];

        BufferedReader fileReader = null;

        try {
        	
            fileReader = new BufferedReader(new FileReader("data.txt"));
            
            int lineIndex = 0;

            while ((line = fileReader.readLine()) != null && lineIndex < numLines) {
            	
                String[] read = line.split(",");
                
                User newUser = new User(read[0], read[1], read[2]);
                
                users[lineIndex] = newUser;
                
                lineIndex++;
            }
        } 
        
        finally {
        	
            fileReader.close();
        }

        int remainingAttempts = 5;

        while (remainingAttempts > 0) {
        	
            System.out.println("Please enter username: ");
            
    		usernameInput = scanner.nextLine().toLowerCase();
    		
            System.out.println("Please enter password: ");
            
            passwordInput = scanner.nextLine();

            User matchedUser = null;


            for (User user : users) {
            	
                if (user.getUsername().equals(usernameInput) && user.getPassword().equals(passwordInput)) {
                    matchedUser = user;
                    break;
                }
            }

            if (matchedUser != null) {

                if (matchedUser.isLocked()) {   
                    break;

                } else {
                    System.out.println("Welcome " + matchedUser.getName());
                    break;
                }
            }
            
            else {
                --remainingAttempts;

                System.out.println("Invalid login, please try again.");
            }
        }

        if (remainingAttempts == 0) {
            System.out.println("Too many failed login attempts, you are now locked out.");
        }

        scanner.close();
    }
}
