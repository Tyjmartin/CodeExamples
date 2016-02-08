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


// Node class for setting the list either right or down.



class Node {
	 Node right;
	 Node down;
	 String item;

	Node (String i, Node r, Node d){
		right = r;
		down = d;
		item = i;
	}
}
