package FileSystem;
import java.util.*;
public class GenericComponent {
	String name;
	boolean isFolder;
	enum type{
		Video,
		Audio,
		Document
	};
	int sizeOfTheFile;
	String content;
	
	public GenericComponent(String name,boolean isFolder,int size) {
		this.name=name;
		this.isFolder=isFolder;
	}
	
	
	
	
	public void insertContent(String content) {
		this.content+=content;
	}
	
}
