/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          Programming Assignment 4: Research Genealogy
// FILE:             GenealogyTree.java
//
// Author: Matthew Shaw, mshaw6@wisc.edu, mshaw6, 001
//
// ---------------- OTHER ASSISTANCE CREDITS 
//
// N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.util.*;
import java.io.*;

/**
 * A general tree that is can be used to keep 
 * track of parent child relationships between data.
 * 
 * Nodes in a general tree can have multiple children.
 * 
 * This tree is built by reading lines from a file.
 * 
 * To help with program 4, this class also has a method
 * that returns a stack of String (names) that are the
 * ancestors of the specified node.  See getAncestorStack.
 */
public class GenealogyTree{

	public static final String LOAD_GENEALOGY_ERROR_MESSAGE = "Error loading genealogy from file";

	// The root node of the GenealogyTree
	private TreeNode<String> root;

	public GenealogyTree(){
		root = null;
	}

	// Get the root node of the GenealogyTree
	public TreeNode<String> getRoot(){
		return root;
	}

	/**
	 * return a Stack that contains the ancestors of the node with matching data
	 * The root is at the bottom of the stack and the matching node is at the top
	 * and the stack contains all ancestors of the matching data node.
	 *
	 * THIS METHOD CALLS A COMPANION (HELPER) METHOD that is recursive
	 * 
	 * If the top of the stack is not target,
	 * return an empty stack indicating that the target
	 * was not found.
	 *
	 * @param target the data you are trying to find
	 * @return a stack with the target data node at top and the root at the bottom,
	 * or return an empty stack if the target was not found.
	 */
	public StackADT<String> getAncestorStack(String target) {
		// DO NOT CHANGE THIS METHOD
		StackADT<String> stack = new Stack<String>();
		stack = getAncestorStack(stack,root,target);
		if (stack.peek().equals(target)) {
			return stack;
		}
		return new Stack<String>(); // empty stack
	}

	/**
	 * Perform a pre-order traversal of the current node and 
	 * return a Stack that contains the ancestors of the target node.
	 * 
	 * The root is at the bottom of the stack and the matching node is at the top
	 * and the stack contains all and only ancestors of the matching data node.
	 * 
	 * NOTE: If target data is not found, the stack returned does not have
	 * target at the top.  Be sure to check this in the calling method.
	 *
	 * @param target the data you are trying to find
	 * @return a stack with the target data node at top and the root at the bottom 
	 * or an empty stack if target is not found
	 */
	private StackADT<String> getAncestorStack(StackADT<String> st, TreeNode<String> curr, String target) {
		
		// if current node is not null
		if ( curr != null ) {
			// push to stack
			st.push(curr.getData());
			// check for match
			if( st.peek().equals(target) ) {
				// if curr matches target return stack
				return st;
			}
			// otherwise, iterate through children
			ListADT<TreeNode<String>> children = curr.getChildren();
			Iterator<TreeNode<String>> childItr = children.iterator();
//			StackADT<String> temp = new Stack<String>();
			// for each child
			while ( childItr.hasNext() ) {
				// get the ancestor stack for that child
				st = getAncestorStack( st, childItr.next(), target) ;
				// if top of ancestry stack equals target
				if ( st.peek().equals(target) ) {
					// return stack
					return st;
				} else {
				// otherwise, pop the top from the stack
					st.pop();
				}
			}
		}
		// return stack if done processing ch
		return st;
	}

	/**
	 * Load a tree from file.
	 *
	 * If there are IOException when loading the tree from file, 
	 * print LOAD_GENEALOGY_ERROR_MESSAGE 
	 * and throw the IOException.
	 *
	 * All the lines in the file "parent -> children" relationships.
	 *
	 * The relationships are listed in a pre-order traversal order starting from root.
	 *
	 * For example, for the following tree:
	 *     a
	 *  /  |  \
	 *  b  c  d
	 *  |     | \
	 *  e     f g
	 *
	 * The input file must follow this form:
	 * a -> b
	 * a -> c
	 * a -> d
	 * b -> e
	 * d -> f
	 * d -> g
	 *
	 * Note: all lines of a file must contain a relationship to be a valid format.
	 * Blank lines will cause exceptions.
	 * 
	 * Pseudocode for the work done by this method:
	 * 
	 * 	// Create a queue, add each new node to the queue
	 *	// create a Scanner connect to the file
	 *  // for each line of the file
	 *  	// read the line
	 *      // parse the line into parent and child
	 *
	 *      // if root is null
	 *      	// create the root node
	 *          // add the root's first child
	 *          // add the root and child to the queue
	 *
	 *      // else Construct other TreeNode
	 *      	// while queue is not empty
	 *          	// get next node from queue without removing it from queue
	 *              // if "front" node matches the parent
	 *              	// create a TreeNode for the child
	 *                  // add the child node to the current "front" node (its parent)
	 *                  // add the child to the queue
	 *                  // break out of the loop
	 *              	// else dequeue the front node 
	 *
	 *  // catch IO exceptions, display error message and rethrow the exception
	 *	// close the file scanner
	 * 
	 */
	public void buildFromFile(String filename) throws IOException{

		// Create a queue, add each new node to the queue
		Queue<TreeNode<String>> queue = new Queue<TreeNode<String>>();
		// create a Scanner connected to the file
		try {
			File file = new File(filename);
			Scanner fileIn = new Scanner( file );
			// for each line of the file
			while ( fileIn.hasNextLine() ) {
				// read the line
				String line = fileIn.nextLine();
				// check that the line is formatted properly
				if ( !line.contains(" -> ") ) throw new IOException();
				// parse the line into parent and child
				String[] parsedLine = line.split(" -> ");
				// if root is null
				if ( root == null ) {
					// create the root
					root = new TreeNode<String>( parsedLine[0] );
					// add its first child
					TreeNode<String> child = new TreeNode<String>( parsedLine[1] );
					root.addChild( child );
					// add the root and child to the queue
					queue.enqueue(root);
					queue.enqueue(child);
				// else Construct other TreeNode
				} else {
					// while queue is not empty
					while ( !queue.isEmpty() ) {
						// get next node from queue without removing it from queue
						TreeNode<String> liveNode = queue.element();
						// if "front" node matches the parent
						if ( liveNode.getData().equals(parsedLine[0]) ) {
							// create a TreeNode for the child
							TreeNode<String> child = new TreeNode<String>( parsedLine[1] );
							// add the child node to the current "front" node (its parent)
							liveNode.addChild(child);
							// add the child to the queue
							queue.enqueue(child);
							// break out of the loop
							break;
						// else dequeue the front node 
						} else {
							queue.dequeue();
						}
					}
				}
			}
			// close the file scanner
			fileIn.close();
		// catch IO exceptions, display error message and rethrow the exception
		} catch ( IOException e ) {
			System.out.println(LOAD_GENEALOGY_ERROR_MESSAGE);
			throw new IOException(e);
		}
		
	}            

	/**
	 * Display the contents of the tree in a horizontal tree form.
	 * 
	 * This method is a private recursive helper method for the
	 * printTree() method.
	 * 
	 * It uses the indentation levels to indicate how many 
	 * dots (two per each level) to print for the node
	 * 
	 * @param current node to print
	 * @param indent_count indicates how many dots .. to print for the current level
	 * @param indent_str indicates string of characters precede each print level
	 */
	private void printTreeWithIndent(TreeNode<String> current, int indent_count, String indent_str){
		
		// visit current node
		System.out.println( current.getData() );
		// increment indent count to next level
		indent_count++;
		
		// get children
		LinkedList<TreeNode<String>> children = (LinkedList<TreeNode<String>>) current.getChildren();
		
		// visit each child
		for ( int i = 0; i < children.size(); i++ ) {
			
			// Prints the specified indent string for each level
			for ( int j = 0; j < indent_count; j++) {
				System.out.print(indent_str);
			}
			printTreeWithIndent( children.get(i), indent_count, indent_str );
		}
	}

	/**
	 * Print a tree with indent.
	 *
	 * You should use pre-order to print a tree, which means:
	 * (1) Print the data at current node
	 * (2) For all children nodes of current node,
	 *       recursively use pre-order to print children nodes.
	 *
	 * Each line of output represents a node, use indent (number of spaces before node data)
	 * to indicate which level the current node belongs to.
	 * For root node (at level 0), use 0 spaces.
	 * For nodes at other levels, add 2 spaces of indent each level.
	 *
	 * Like for the following tree:
	 *     a
	 *  /  |  \
	 *  b  c  d
	 *  |     | \
	 *  e     f g
	 *
	 * The displayed output should be:
	 * <pre>
	 *  a
	 *  ..b
	 *  ....e
	 *  ..c
	 *  ..d
	 *  ....f
	 *  ....g
	 * </pre>
	 */
	public void printTree() {
		// call recursive helper method
		printTreeWithIndent(root, 0, "..");
	}
}
