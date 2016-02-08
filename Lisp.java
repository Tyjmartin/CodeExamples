/*
 *
 *
 *Tyler Martin
 *4836318
 *
 *COSC 2P03
 *
 *Tuesday September 25 2012
 *
 *
 *Ok so once I brought it to school it started doing some weird stuff, so if something does not work email me at tm10vy@brocku.ca.
 *I had everything working on my computer at home, except for the copy method so I do not know what would be causing the strange errors
 *I am getting now. Everything should work fine though.
 */




package Assign1;

import java.util.Scanner;

public class Lisp {

	Node Head;

	public Lisp(){

		String command;
		Head = null;

		int numR, numL;

		while (true) // forever loop, so you can continue to set and print to your hearts content
		{
			Scanner sc = new Scanner(System.in); //Scanner to take in what is typed

			String sample = sc.nextLine();

			Parser ps = new Parser(sample);
			numR = ps.numParenR();
			numL = ps.numParenL();
			
			command = ps.next_Token();

				if (command.compareToIgnoreCase("setq")==0){ //testing to see what the command was, found comparetoignorecase while looking up compareto.
					setq(ps);
				}
				else if (command.compareToIgnoreCase("print")==0){
					print(ps);
				}
				else if (command.compareToIgnoreCase("quit")==0){ // yay a quit command!!!
					break;
				}
			}
	}

	private void setq(Parser str){
		String setStr,varStr;  // so setstr is goign to be used to check the variable and set it then checks for the literal marker
		varStr = str.next_Token(); // grabbing the variable
		setStr = str.next_Token(); // getting the next token to check and make sure it is the literal marker
		if (setStr.compareTo("'")==0){
			Head = new Node (varStr,Head,null);  //setting the right node
			setStr = str.next_Token();
			Head.down = insertList(Head.down,str);
		}
		else if (setStr.compareToIgnoreCase("nil")==0){
			sever(varStr);
		}
		/*else{
			Node temp = Head;  // I actually dont know how to copy properly, i tried a few different ways and it just didnt work.
			while (temp!=null){
				if (temp.item.compareToIgnoreCase(setStr)==0){
					Head = new Node (varStr,Head,null);
					copy(temp);
				}
				else
					temp = temp.right;
			}*/
	}

	private Node insertList(Node value, Parser info){ // figured out i could not build the recursion in the actual setq
		Node temp;
		String strValue;
		value = new Node("",null,null);
		temp = value;
		strValue = info.next_Token();

		while (true){
			if (strValue.compareTo("(")==0){   // Ok so ideally this should check to see if the value is a ( and if it is it will recurse?
				temp.right = new Node ("",null,null);
				temp = temp.right;
				temp.down = insertList(temp.down,info);
			}
			else {
				if (strValue.compareTo(")")==0){ // will break the recursion? if it is a )
					break;
				}
				else
					temp.right = new Node (strValue,null,null); // inserts the value?
					temp = temp.right;
					System.out.println(temp.item);
			}
			strValue = info.next_Token();
		}
		return value;
	}

	private void print(Parser info){
		String strValue;
		Node temp;
		temp = Head;

		if (info.hasToken()) //checking to see if there is a variable
		{
			strValue = info.next_Token(); //taking that variable
			while(temp.right!=null && temp.item.compareToIgnoreCase(strValue)!=0) //looking for the variable in the list
			{
				temp = temp.right; // traversing the list to find the right value
			}
				if (temp!=null && temp.item.compareToIgnoreCase(strValue)==0) //it finds the value that you want to print
				{
					System.out.print(temp.item);
					printRec(temp.down);
					System.out.print("\n");
				}
				else
					System.out.print("NIL \n");
		}
		else
		{
			while (temp!=null)
			{
				System.out.print(temp.item);
				printRec(temp.down);
				System.out.print("\n");
				temp = temp.right;
			}
		}

	}

	private void printRec(Node info)
	{ // ok so just like setting the list needed a secondary function to do the recursion
		Node temp;  // temp node
		temp = info;  // setting the temp node
		System.out.print(" ( "); //if i understand recusion properly this should print everytime we recurse?
		 while (temp!=null) //ok so as long as the linkedlist is not empty?
		 {
		 	if (temp.down!=null)
		 	{ //as long as there is a node pointing down?
		 		printRec(temp.down); //recurse??
		 	}
		 	System.out.print(temp.item);
		 	temp = temp.right;
		 }
		 System.out.print(" ) ");
	}

	private void copy(Node value){
		//no idea how to copy!!!
	}
	private void sever(String value){
		Node current,temp;
		current = Head;
		temp = null;
		while (current!=null){
			if (current.item.compareToIgnoreCase(value)==0){
				if (temp == null){
					Head = current.right;
					current = Head;
				}
				else{
					temp.right = current.right;
					current = temp.right;
				}
			}
			else{
				temp = current;
				current = current.right;
			}
		}

	}

	public static void main (String args[]) {new Lisp();}

}
