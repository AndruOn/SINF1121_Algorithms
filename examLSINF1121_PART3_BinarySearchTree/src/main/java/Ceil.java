
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
		if (root == null) return null;
		Integer root_val = root.getValue();
		if (value == root_val) return value;
		if (value > root_val) return ceil(root.getRight(),value);
		Integer left_value = ceil(root.getLeft(),value);
		if (left_value != null) return left_value;
		return root_val;
    }
}
