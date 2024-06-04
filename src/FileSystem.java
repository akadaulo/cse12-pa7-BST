import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileSystem {

    BST<String, FileData> nameTree;
    BST<String, ArrayList<FileData>> dateTree;
    
    // TODO
    public FileSystem() {
        nameTree = new BST<>();
        dateTree = new BST<>();
    }


    // TODO
    public FileSystem(String inputFile) {
    	// Add your code here
        this();
        try {
            File f = new File(inputFile);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(", ");
                // Add your code here
                if(data.length == 3){
                    add(data[0], data[1], data[2]);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);

        }
    }


    // TODO
    public void add(String name, String dir, String date) {
    	FileData fileData = new FileData(name, dir, date);
        nameTree.put(name, fileData);

        ArrayList<FileData> filesByDate = dateTree.get(date);
        if (filesByDate == null) {
            filesByDate = new ArrayList<>();
            dateTree.put(date, filesByDate);
        }
        filesByDate.add(fileData);
    }


    // TODO
    public ArrayList<String> findFileNamesByDate(String date) {
        ArrayList<String> fileNames = new ArrayList<>();
        ArrayList<FileData> filesByDate = dateTree.get(date);
        if (filesByDate != null) {
            for (FileData fileData : filesByDate) {
                fileNames.add(fileData.getName());
            }
        }
        return fileNames;
    }


    // TODO
    public FileSystem filter(String startDate, String endDate) {
        FileSystem filteredFileSystem = new FileSystem();
        ArrayList<String> dateKeys = new ArrayList<>(dateTree.keys());
        
        // Iterate through date keys
        for (String date : dateKeys) {
            // Check if the current date is within the range
            if (isDateInRange(date, startDate, endDate)) {
                ArrayList<FileData> filesByDate = dateTree.get(date);
                if (filesByDate != null) {
                    // Add files to the filtered file system
                    for (FileData fileData : filesByDate) {
                        filteredFileSystem.add(fileData.getName(), fileData.getDirectory(), fileData.getModifiedDate());
                    }
                }
            }
        }
        return filteredFileSystem;
    }

    private boolean isDateInRange(String date, String startDate, String endDate) {
        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) < 0;
    }
    
    
    // TODO
    public FileSystem filter(String wildCard) {
        FileSystem filteredFileSystem = new FileSystem();
        for (String name : nameTree.keys()) {
            if (name.contains(wildCard)) {
                FileData fileData = nameTree.get(name);
                filteredFileSystem.add(fileData.getName(), fileData.getDirectory(), fileData.getModifiedDate());
            }
        }
        return filteredFileSystem;
    }
    
    
    // TODO
    public List<String> outputNameTree(){
        List<String> outputList = new ArrayList<>();
        for (String name : nameTree.keys()) {
            FileData fileData = nameTree.get(name);
            outputList.add(name + ": " + fileData.toString());
        }
        return outputList;
    }
    
    
    // TODO
    public List<String> outputDateTree(){
        List<String> outputList = new ArrayList<>();
        for (String date : dateTree.keys()) {
            List<FileData> filesByDate = dateTree.get(date);
            for (FileData fileData : filesByDate) {
                outputList.add(date + ": " + fileData.toString());
            }
        }
        return outputList;
    }
    

}

