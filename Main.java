package FileSystem;
import java.util.*;

/// We need to build a filesystem that
///makes us move from one folder to other
///create file
///copy a file
///move a file
///delete a file
///
public class Main {	
	public static void main(String [] a) {
		RootFileSystem fs =RootFileSystem.getInstance();

		boolean runSystem = true;
		Scanner sc = new Scanner(System.in);
		while(runSystem) {
			System.out.println("Welcome to Nover Subsystem");
			System.out.println("You are at the "+fs.movingNode.name);
			System.out.println("Select the operations that you want to choose");
			System.out.println("1.Create a folder/file");
			System.out.println("2.Print current directory");
			System.out.println("3.List the files in current directory");
			System.out.println("4.Copy a file/folder");
			System.out.println("5.Move a file/folder");
			System.out.println("6.Paste the file/folder");
			System.out.println("7.Delete the file/folder");
			System.out.println("8.Move Directory ");
			System.out.println("9.Fall Back directory");
			System.out.println("10.Exit");
			
			int choice = Integer.parseInt(sc.next().trim());
					
			switch(choice) {
			case 1:
				System.out.println("Is it a folder [T] or a file [F]");
				char c = sc.next().charAt(0);
				System.out.println("Enter the name of the file");
				sc.nextLine();
				String name= sc.nextLine().trim();
				if(c=='T') {
					fs.createFile(name, true, 100);
				}else if(c=='F'){
					fs.createFile(name,false,1000);
				}else {
					System.out.println("Invalid Option");
				}
				
				break;
			case 2:	
				fs.printDirectory();
				break;
			case 3:
				fs.listFiles();
				break;
			case 4:
				System.out.println("Enter the file number you want to copy :"); 
				fs.listFilesAtCurrentDirectory();
				 int cpyIndex=sc.nextInt()-1;
				 if(cpyIndex<0 || cpyIndex>=fs.movingNode.next.size()) {
					 System.out.println("You are entering invalid file number");
					 return;
				 }
				 fs.copyFile(cpyIndex);				 
				break;
			case 5:
				System.out.println("Enter the file number you want to move :"); 
				fs.listFilesAtCurrentDirectory();
				 int mvIndex=sc.nextInt()-1;
				 if(mvIndex<0 || mvIndex>=fs.movingNode.next.size()) {
					 System.out.println("You are entering invalid file number");
					 return;
				 }
				 fs.moveFile(mvIndex);		
				break;
			case 6:
				fs.listFilesAtCurrentDirectory();
				System.out.println("Do you wanna paste it here (T/F)");
				char d = sc.next().charAt(0);
				if(d=='T') {
					fs.pasteFile();
				}
				break;
			case 7:
				System.out.println("Enter the file number you want to delete :"); 
					fs.listFilesAtCurrentDirectory();
					int delIndex=sc.nextInt()-1;
					if(delIndex<0 ||  delIndex>fs.movingNode.next.size()) {
						System.out.println("Invalid file option to delete");
						return;
					}
					fs.deleteFile(delIndex);
				break;
			case 8:
				fs.listFilesAtCurrentDirectory();
				int directIndex=sc.nextInt()-1;
				if(directIndex<0 ||  directIndex>fs.movingNode.next.size()) {
					System.out.println("Invalid file option to move");
					return;
				}
				fs.moveDirectory(directIndex);
				break;
			case 9:
				fs.fallBackDirectory();
				break;
			case 10:
				System.out.println("-".repeat(30));
				System.out.println("Exiting system....");
				runSystem=false;
				break;
			default:
				System.out.println("-".repeat(30));
				System.out.println("Enter a valid option");
				sc.close();
				break;
			}
		}
	}

}
