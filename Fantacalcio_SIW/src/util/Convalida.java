package util;

import java.util.regex.Pattern;

public class Convalida {

	/** check whether email and password are valid */
	public boolean validateParameters(final String... params) {
		for (String string : params) {
			if (string == null || string.equals(""))
				return false;
		}
		return true;
	}

	/** check whether both password are equals */
	public boolean controlEqualsPasswords(String password1, String password2) {
		return password1.equals(password2);
	}
	
	public boolean checkInputPassword(String input){
		/** La password deve contenere almeno un numero e almeno una lettera o min o Maiusc */
		String emailPattern = "(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,16})$";
		
		if(Pattern.matches(emailPattern, input))
			return true;
		else
			return false;
	}
	public boolean checkInputUsername(String usernameEntered){
		/**Only letter and digit */ 
		String reg = "/^[0-9a-zA-Z]{3,15}$/";
		if(Pattern.matches(reg, usernameEntered))
			return true;
		else
			return false;
	}
	
	/** check the correct input of a e-mail */
	public boolean checkInputEmail(String emailEntered){
		String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if(Pattern.matches(emailPattern, emailEntered))
			return true;
		else
			return false;
	}

	
}
