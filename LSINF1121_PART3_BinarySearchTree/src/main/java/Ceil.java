import java.util.HashMap;

public class Ceil {
	/**
	 * Find in the tree the smallest element greater than or equal to value
	 * (so either the element itself or the element located directly after it
	 * in order of magnitude). If such an element does not exist,
	 * it must return null.
	 *
	 * Inserez votre reponse ici
	 */
	public static Integer ceil(Node root, int value) {
		new HashMap<>();
		if (root == null) return null;
		if (value > root.getValue())  return ceil(root.getRight(),value);
		else if (value == root.getValue())  return root.getValue();
		Integer t = ceil(root.getLeft(),value);
		if(t != null) { return t;}
		return root.getValue();
    }
}
