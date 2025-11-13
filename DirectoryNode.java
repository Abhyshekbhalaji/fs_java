package FileSystem;
import java.util.*;
public class DirectoryNode {
DirectoryNode parent;
List<DirectoryNode> next;
String name;
Set<String> childNames;
boolean isFolder;
	public DirectoryNode(DirectoryNode parent,String currPosition,boolean isFolder) {
			this.parent=parent;
			this.name=currPosition;
			this.isFolder=isFolder;
			this.childNames=new HashSet<>();
			this.next=new ArrayList<>();
	}
	

	public DirectoryNode getParent() {
		if(this.parent!=null) {
			return this.parent;
		}
		return null;
	}
	public boolean hasNext() {
		return this.next!=null;
	}
}
