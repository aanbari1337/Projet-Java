package Utils;

import java.awt.Component;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Utils {
	
	private static MessageDigest md;
		
	public static int periodCalcul(java.sql.Date date) {
		
		int period = 0;
		
		LocalDate currentDate = LocalDate.now();
		
		period = Period.between(date.toLocalDate(), currentDate).getYears();
		
		return period;
	}
	
	 public static String generatePassword(int length) {
		 
	      String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
	      String specialCharacters = "!@#$";
	      String numbers = "1234567890";
	      String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
	      Random random = new Random();
	      char[] password = new char[length];

	      password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
	      password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
	      password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
	      password[3] = numbers.charAt(random.nextInt(numbers.length()));

	      for(int i = 4; i< length ; i++) {
	         password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
	      }
	      return String.valueOf(password);
	   }
	 
	 

	   public static String cryptWithMD5(String pass){
		   
	        try {
				md = MessageDigest.getInstance("MD5");
	        byte[] passBytes = pass.getBytes();
	        md.reset();
	        byte[] digested = md.digest(passBytes);
	        StringBuffer sb = new StringBuffer();
	        for(int i=0;i<5;i++){
	            sb.append(Integer.toHexString(0xff & digested[i]));
	        }
	        return sb.toString();
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return null;
	   }
	 
	 
	 
	 public static Boolean regexVerifier(String regex,String ex) {
		 
		 Pattern pattern = Pattern.compile(regex);
		 Matcher matcher = pattern.matcher(ex);
		 
		 if(matcher.matches() == true)
			 return true;
		 return false;
	 }
	 
	 
	 public static  void displayMessage(Component c,int i) {
		 
		if (i == 0)
			JOptionPane.showMessageDialog(c, "*un employe avec ce cin existe deja");
		else if(i == -1)
			JOptionPane.showMessageDialog(c, "*une erreur s'est produite lors de l'operation");
		else if (i == -2)
			JOptionPane.showMessageDialog(c, "*date de naissance invalide\n-date de recrutement invalide");
		else if (i == -3)
			JOptionPane.showMessageDialog(c, "*date de naissance invalide");
		else if (i == -4)
			JOptionPane.showMessageDialog(c, "*date de recrutement invalide");
	 }
}
