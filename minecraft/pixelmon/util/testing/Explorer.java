package pixelmon.util.testing;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class Explorer extends JTree{
	//Collection col;
	//DefaultMutableTreeNode top = new DefaultMutableTreeNode();
	public Explorer(Collection col){
		super(addNodes(new DefaultMutableTreeNode(), col));
	}
	
	public Explorer(Map map){
		super(addNodes(new DefaultMutableTreeNode(), map));
	}
	
	public static void showSimpleExplorer(Collection col, String name){
		Explorer expl = new Explorer(col);
		JScrollPane treeView = new JScrollPane(expl);
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(treeView);
		frame.pack();
		frame.setVisible(true);
	}
	
	protected static DefaultMutableTreeNode addNodes(DefaultMutableTreeNode branch, Map map){
		Set<Map.Entry> entries = map.entrySet();
		for(Map.Entry e : entries){
			DefaultMutableTreeNode item = new DefaultMutableTreeNode(e.getKey());
			branch.add(item);
			if(e.getValue() instanceof Collection){
				addNodes(item, (Collection)e.getValue());
			}
			else if(e.getValue() instanceof Map){
				addNodes(item, (Map) e.getValue());
			}
			else
				item.add(new DefaultMutableTreeNode(e.getValue()));
		}
		return branch;
	}
	
	protected static DefaultMutableTreeNode addNodes(DefaultMutableTreeNode branch, Collection collection){
		for(Object o : collection){
			DefaultMutableTreeNode item = new DefaultMutableTreeNode(o);
			branch.add(item);
			if(o instanceof Collection){
				addNodes(item, (Collection) o);
			}
		}
		return branch;
	}
	
}
