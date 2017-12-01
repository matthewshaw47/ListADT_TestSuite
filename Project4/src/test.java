import java.io.IOException;

public class test {
	
	public static TreeNode<String> tree;
	public static GenealogyTree geoTree;
	
	
	public static void main(String[] args) {
		
		
		// Testing of GenealogyTree class
		// Manual building of a tree structure to test
//		TreeNode<String> tree = new TreeNode<String>("Root");
//		tree.addChild("Left");
//		tree.addChild("Middle");
//		tree.addChild("Right");
//		LinkedList<TreeNode<String>> children = (LinkedList<TreeNode<String>>) tree.getChildren();
//		for ( int i = 0; i < children.size(); i++) {
//			children.get(i).addChild("childLeft" + i);
//			children.get(i).addChild("childRight" + i);
//		}
//		test test = new test();
//		test.setTree(tree);
		
		// Testing of loading a tree from a file
		GenealogyTree geoTree = new GenealogyTree();
		test.setGeotree(geoTree);
		try {
			geoTree.buildFromFile("input1.txt");
		} catch ( IOException e ) {
			System.out.println("ERROR");
			System.exit(0);
		}
		
		// Begin method tests, first testing if it prints a tree properly
		geoTree.printTree();
		
		// Testing Ancestor methods
		Ancestor ancest = new Ancestor();
		
		System.out.print("\nLowest Common Ancestor:\n\t");
		System.out.println( ancest.lowestCommonAncestor("d", "e") );
		
//		// Checking getAncestorStack builds the correct stacks
//		StackADT<String> stackRoot = geoTree.getAncestorStack("Root");
//		StackADT<String> stackChild = geoTree.getAncestorStack("Middle");
//		StackADT<String> stackGrandchild = geoTree.getAncestorStack("childRight2");
//		System.out.println("\nRoot search: " + stackRoot.peek() );
//		System.out.println("Child search: " + stackChild.peek() );
//		System.out.println("Grandchild search: " + stackGrandchild.peek() );
//		System.out.print("\nRoot stack: \n\t");
//		while ( !stackRoot.isEmpty() ) {
//			System.out.print( stackRoot.pop() + " " );
//		}
//		System.out.print("\nChild stack: \n\t");
//		while ( !stackChild.isEmpty() ) {
//			System.out.print( stackChild.pop() + " " );
//		}
//		System.out.print("\nGrandchild stack: \n\t");
//		while ( !stackGrandchild.isEmpty() ) {
//			System.out.print( stackGrandchild.pop() + " " );
//		}
		
		// Test of functionality of stack and queue methods
//		Stack<String> stack = new Stack<String>();
//		Queue<String> queue = new Queue<String>();
//		
//		for ( int i = 0; i < 5; i++ ) {
//			stack.push("" + i);
//			queue.enqueue("" + i);
//		}
//		
//		//stack = (Stack<String>) stack.reverse();
//		
//		System.out.println("Contents of Stack:");
//		
//		while ( !stack.isEmpty() ) {
//			System.out.print( "\tWill remove: " + stack.peek() );
//			System.out.println( "\tRemoved: " + stack.pop() );
//		} 
//		
//		System.out.println("Contents of Queue:");
//		
//		while ( !queue.isEmpty() ) {
//			System.out.print( "\tWill remove: " + queue.element() );
//			System.out.println( "\tRemoved: " + queue.dequeue() );
//		}
		
		
		// Testing of elements within the LinkedList class only 
//		LinkedList<String> list = new LinkedList<String>();
//		
//		for ( int i = 0; i < 5; i++ ) {
//			list.add("" + (i+1) );
//		}
//		System.out.println("Removed: " + list.remove(2) + "\n");
//		list.add( 2, "Hello");
//		System.out.println("Get 3rd element: " + list.get(2) + "\n");
////		System.out.println("LinkedLIst contains 0?: " + list.contains("0") + "\n");
////		System.out.println("Contents of the LinkedList: ");
////		
////		Listnode<String> temp  = list.getHeaderNode().getNext();
////		
////		while ( temp != null ) {
////			System.out.println("\t" + temp.getData() );
////			temp  = temp.getNext();
////		}
////		System.out.println("DONE");
//		///////////////////////////////////////////////////////
//		
//		LinkedListIterator<String> itr = list.iterator();
//		Listnode<String> temp;
//		
//		while ( itr.hasNext() ) {
//			System.out.println( itr.next() );
//		}
	}
	
	public static TreeNode<String> getTree() {
		return tree;
	}
	
	public static void setTree(TreeNode<String> tree) {
		test.tree = tree;
	}
	public static GenealogyTree getGeotree() {
		return geoTree;
	}
	
	public static void setGeotree(GenealogyTree geoTree) {
		test.geoTree = geoTree;
	}
	
}
