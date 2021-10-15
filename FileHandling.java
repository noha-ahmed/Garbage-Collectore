import javax.print.attribute.standard.SheetCollate;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FileHandling {
    public static ArrayList<Node> loadHeapInArray(String heapFilePath){
        ArrayList<Node> heapArray = new ArrayList<Node>();
        try (BufferedReader br = new BufferedReader(new FileReader(heapFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                values[0] = values[0].replaceAll("[^0-9]+", "");
                heapArray.add(new Node( Integer.parseInt(values[0]),Integer.parseInt(values[1]),Integer.parseInt(values[2])));
               // System.out.println(""+heapArray.get(i).getId()+"  "+heapArray.get(i).getMemory_start()+"   "+heapArray.get(i).getMemory_end());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found !");
        } catch (IOException e) {
            System.out.println("Error ! load in array");
        }

        return  heapArray;
    }

    public static HashMap<Integer,Node> loadHeapInHashMap(String heapFilePath){
        HashMap<Integer,Node> heapHashMap = new HashMap<Integer,Node>();
        try (BufferedReader br = new BufferedReader(new FileReader(heapFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                values[0] = values[0].replaceAll("[^0-9]+", "");
                heapHashMap.put(Integer.parseInt(values[0]),new Node( Integer.parseInt(values[0]),Integer.parseInt(values[1]),Integer.parseInt(values[2])));
              //  System.out.println(heapHashMap.get(Integer.parseInt(values[0])).getId());

            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found !");
        } catch (IOException e) {
            System.out.println("Error ! load in hash" );
        }
        return  heapHashMap;
    }

    public static HashMap<Integer,Node> loadArrayInHashMap(ArrayList<Node> heapArray ){
        HashMap<Integer,Node> heapHashMap = new HashMap<Integer,Node>();
        for( int i = 0 ; i < heapArray.size() ; i++ ){
            heapHashMap.put( heapArray.get(i).getId() , heapArray.get(i) );
        }
        return heapHashMap;
    }

    public static void linkNodes(String pointersFilePath , HashMap<Integer,Node> heapHashMap){
        try (BufferedReader br = new BufferedReader(new FileReader(pointersFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                values[0] = values[0].replaceAll("[^0-9]+", "");
                heapHashMap.get(Integer.parseInt(values[0])).addChild(heapHashMap.get(Integer.parseInt(values[1])));
               // System.out.println(heapHashMap.get(Integer.parseInt(values[0])).getId()+"   "+heapHashMap.get(Integer.parseInt(values[0])).getChildren().get(0).getId());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found !");
        } catch (IOException e) {
            System.out.println("Error in link nodes!");
        }
    }

    public static ArrayList<Integer> loadRoots(String rootFilePath){
        ArrayList<Integer> roots = new ArrayList<Integer>();
        try {
            FileReader reader = new FileReader(rootFilePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                roots.add(Integer.parseInt(line));
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("Error in load roots!");
        }
        return  roots;
    }

    public static File storeHeap( ArrayList<Node> heapArray , String fileName ){
        File newHeapFile =null;
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            for( Node node : heapArray ){
                StringBuilder line = new StringBuilder();
                line.append(String.valueOf(node.getId()));
                line.append(',');
                line.append(String.valueOf(node.getMemory_start()));
                line.append(',');
                line.append(String.valueOf(node.getMemory_end()));
                line.append('\n');
                writer.write(line.toString());
                System.out.println(line.toString());
            }



        } catch (FileNotFoundException e) {
            System.out.println("File Not Found !");
        }
        return newHeapFile;
    }


}
