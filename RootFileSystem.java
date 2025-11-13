package FileSystem;
import java.util.*;
public class RootFileSystem {
private static RootFileSystem instance=null;
public DirectoryNode root;
public DirectoryNode movingNode;
public Set<String> childNames=new HashSet<>();
public DirectoryNode lastCopied=null;
// root 
/////  -> folder1
////   -> folder2
///   -> folder3
///   -> text.txt
/// move to folder1 -> root.children -> has a folder name folder 1
///                                  -> I will move my movingNode to folder1
/// in order to print the directory you need to do dfs from movingNode to root
/// copy,move,delete
/// copy needs two inputs folder/file , location to be copied
///	
///
///
/// 
public void initalizeRoot() {
	root=new DirectoryNode(null,"root",true);
	movingNode=root;
}


private RootFileSystem() {
	initalizeRoot();
}
public static RootFileSystem getInstance() {
	if(instance==null) {
	instance=new RootFileSystem();	
	}
	return instance;
}
	
public void printDirectory() {
List<String> dir = new ArrayList<>();
	DirectoryNode temp = movingNode;
	while(temp!=null) {
		dir.add(temp.name);
		temp=temp.getParent();
	}
	for(int i =dir.size()-1;i>=0;i--) {
		System.out.print(dir.get(i) + " /");
	}
	System.out.println();
}

public void dfs(DirectoryNode root,int level) {
	System.out.println("  ".repeat(level+1)+(level+1>1 ? "->":"")+(root.isFolder?"[F] ":"") +root.name);
	
	for(DirectoryNode it : root.next) {

			dfs(it,level+1);
	}
	
}


public void listFiles() {
	DirectoryNode temp = root;
	System.out.println("-".repeat(48));
	dfs(temp,0);
	System.out.println("-".repeat(48));
}
public void moveDirectory(int idx) {
DirectoryNode next= movingNode.next.get(idx);
System.out.println("Moving from "+ movingNode.name+" to "+next.name);
movingNode = next;
}

public void fallBackDirectory() {
	if(movingNode.getParent()!=null) {
		movingNode=movingNode.getParent();
		System.out.println("Moved to "+movingNode.name);
		return;
	}
	System.out.println("You are in the root directory");
};



public void createFile(String name,boolean isFolder,int size) {
		// check input
       if(name.length()==0) {
    	   System.out.println("Name cannot be empty");
    	   return;
       }              
       if(movingNode.childNames.contains(name)) {
    	   
    	   for(DirectoryNode it : movingNode.next) {
    		   if(it.isFolder==isFolder) {
    			   System.out.println("Duplicate names are not allowed");
    	    	   return ;	   
    		   }
    	   }
    	   	
       }
       if(!movingNode.isFolder) {
    	   System.out.println("You cant create a file over another");
    	   return;
       }
       DirectoryNode  file= new DirectoryNode(movingNode,name,isFolder);
    	movingNode.next.add(file);
    	movingNode.childNames.add(name);
		
    	System.out.println("File " + name+" has been created");
	
}

public void currentPos() {
	System.out.println("You are at " +movingNode.name);
}
public void deleteFile(int idx) {
	 movingNode.next.remove(idx);
	 System.out.println("Deleted Successfully");
};

public void listFilesAtCurrentDirectory() {
	System.out.println("Parent Folder : " +movingNode.name);
	System.out.println("-".repeat(30));
	if(movingNode.next.size()==0) {
		System.out.println("No files present currently under this directory");
		return;
	}
	for(int i = 0;i<movingNode.next.size();i++) {
		System.out.println(i+1 + " "+movingNode.next.get(i).name);
	}
}
public void copyFile(int idx) {
	if(this.lastCopied==null) {
		lastCopied=movingNode.next.get(idx);
		System.out.println("File has been copied "+lastCopied.name );
		return;
	}
	System.out.println("An file or folder is already been copied please paste it before trying again");
}



public void renameFile(int idx,String newName) {
		String oldName=movingNode.next.get(idx).name;
		if(newName.length()==0|| oldName.equals(newName) || movingNode.childNames.contains(newName)) {
				System.out.println("New name is not valid");
				return;
		}
		
		
		movingNode.childNames.remove(oldName);
	DirectoryNode it = movingNode.next.get(idx);
	it.name= newName;
	
	movingNode.childNames.add(newName);
	
	System.out.println("Changed name from "+oldName+" to "+newName);
	
}
public void moveFile(int idx) {
	if(this.lastCopied==null) {
		lastCopied=movingNode.next.get(idx);
		movingNode.next.remove(idx);
		System.out.println("File has been copied "+lastCopied.name );
		return;
	}
	System.out.println("An file or folder is already been copied please paste it before trying again");
}

public void pasteFile() {
	if(lastCopied==null) {
		System.out.println("No file to be pasted here");
		return;
	}
	boolean existAsFile =false;
	for(DirectoryNode it : movingNode.next) {
			if(it.name.equals(lastCopied.name) && lastCopied.isFolder==it.isFolder) {
				existAsFile=true;
			}
	}
	boolean exist= movingNode.childNames.contains(lastCopied.name) && existAsFile;
	
	if(!exist) {
		movingNode.next.add(lastCopied);
		lastCopied=null;
		System.out.println("File has been successfully pasted");
		return;
		
	}
	System.out.println("Name with this file already exist");
	;
	
}


}
