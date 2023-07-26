package com.projectsdraft;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class User{
	private String username;
	private String password;
	private String name;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUser(String username, String password, String name) {
		
		this.username = username;
		this.password = password;
		this.name = name;
		
	}
	
	public String getUser() {
		
		return username + password + name;
	}
public static void main(String[] args)throws IOException{
		
	String line = "";
	
	Scanner scanner = new Scanner(System.in);
    int numLines = 4 ;	
    
    String usernameInput;
    
    String passwordInput;

    
	 User users[] = new User[numLines];
	
	BufferedReader fileReader = null;
	
	try {
		
		
		 fileReader = new BufferedReader(new FileReader("data.txt"));		
		 int lineIndex = 0;
		
	while((line = fileReader.readLine()) != null && lineIndex < numLines ) {
		
		String[] read = line.split(",");
		
		
		User newUser = new User();
		
		newUser.setUser(read[0], read[1], read[2]);
		
		
		users[lineIndex] = newUser;
		
		lineIndex++;
		
	}
	
		
}	
	
	finally{
		
		fileReader.close();
	}
	
	
	boolean infoFound = false;
	
    int chances = 5;

	while(chances > 0 && !infoFound) {
		
		System.out.println("Enter a username");
		
		usernameInput = scanner.nextLine().toLowerCase();
		
		System.out.println("Enter a password");
		
		passwordInput = scanner.nextLine().toLowerCase();

	for(User checkInfo : users) {
		
		if(usernameInput.equals(checkInfo.getUsername()) && passwordInput.equals(checkInfo.getPassword())) {

			infoFound = true;
			
			System.out.println("Welcome " +  checkInfo.getName() );
			
		break;	
		}
		
	
	
	}
	
	if(!infoFound && chances > 0) {
		
		System.out.println("Invalid login, please try again.");
		
		chances--;
	
}
	}
	
	if(chances == 0) {
		System.out.println("Too many failed login attempts, you are now locked out.");
		
	}
	
	scanner.close();

	
}
}