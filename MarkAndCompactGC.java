import java.util.ArrayList;
import java.util.HashMap;

public class MarkAndCompactGC {
    private ArrayList<Node> heapArray;
    private HashMap<Integer, Node> heapHashMap;
    private ArrayList<Integer> roots;

    public MarkAndCompactGC(  String heapPath , String rootsPath , String pointersPath ){
        this.roots= FileHandling.loadRoots(rootsPath);
        this.heapArray = FileHandling.loadHeapInArray(heapPath);
        this.heapHashMap =FileHandling.loadArrayInHashMap(heapArray);
        FileHandling.linkNodes(pointersPath,heapHashMap);
    }

    public ArrayList<Node> markAndCompactGC(){
        mark();
        compact();
        return heapArray;
    }
    private void markNode ( Node node ){
        if( node.isMarked() ) return;
        node.setMarked(true);
        for( Node child : node.getChildren() ) markNode(child);
    }

    private void mark (){
        for( int rootId : roots ){
            markNode(heapHashMap.get( rootId ));
        }
    }

    public void printMarked(){
        for( Node node : heapArray ){
            System.out.println(node.getId() + " " + node.isMarked());
        }
    }

    private void compact(){
        int p = 0;
        for ( int i = 0 ; i < heapArray.size() ; i++ ){
            Node node = heapArray.get(i);
            if( node.isMarked() ){
                node.setMemory_start(p);
                node.setMemory_end( p + node.getSpaceTaken() - 1 );
                p += node.getSpaceTaken();
            }
            else{
                heapArray.remove(node);
                i--;
            }
        }
    }

    private void printCompact(){

        System.out.println("--------------------------");
        for( Node node : heapArray ){
            System.out.println(node.getId() + " " + node.isMarked() + " " + node.getMemory_start() + " " + node.getMemory_end());
        }
    }



}
