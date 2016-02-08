/*
 *
 *
 *Tyler Martin
 *4836318
 *
 *COSC 2P03
 *
 *Tuesday September 25 2012
 */



package Assign1;

import java.io.*;
import java.util.StringTokenizer;

class Parser {

	StringTokenizer st;
	int countParenL = 0;
	int countParenR = 0;

	public Parser(String a){
		a = addspace(a); // takes in the string and adds the spaces
		st = new StringTokenizer(a," ");	// magical tokenizer makes nice tokens to work with!!!
	};

	public String addspace(String a){
		int counter = 0; // typical counter
		String parse; //string used to build our new string with spaces
		parse = "";

		// builds a string from the input, and replacing those values where needed.
		while (counter<a.length()){
			if (a.charAt(counter)=='\''){
				parse = parse +" ' ";
			}
			else if (a.charAt(counter)=='('){
				parse = parse +" ( ";
				countParenL++;
			}
			else if (a.charAt(counter)==')'){
				parse = parse +" ) ";
				countParenR++;
			}
			else parse = parse + a.charAt(counter);

			counter++;
		}


		/*Leaving this here because it was interesting to use
		 *and want to be able to come back and look at it in
		 *the future if necessary.
		 *Problem was not being able to insert a space in
		 *front of the (, so output was coming out as " ( r( rr ) )
		 *creating a token out of r(.
		 *
		 *StringBuffer strBuff = new StringBuffer(a);

		while (counter<strBuff.length()){
			if (strBuff.charAt(counter)=='\'')
			{
				strBuff.insert(counter+1,' ');
				counter++;
			}
			else if (strBuff.charAt(counter)=='(')
			{
				strBuff.insert(counter+1,' ');
				counter++;
			}
			else if (strBuff.charAt(counter)==')')
			{
				strBuff.insert(counter+1,' ');
				counter=counter+2;
			}
			else counter++;
		}*/
		return parse;
	}

	public String next_Token() {	// give you the next token
		return st.nextToken();
	}
	public boolean hasToken(){     // boolean to make sure there is another token!!
		return st.hasMoreTokens();
	}

	public int numParenL (){
		return countParenL;
	}
	public int numParenR (){
		return countParenR;
	}

}
