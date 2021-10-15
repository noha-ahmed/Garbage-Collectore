import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CopyGC {

    private HashMap<Integer, Node> heapHashMap;
    private ArrayList<Integer> roots;

    public CopyGC(  String heapPath , String rootsPath  , String pointersPath ){
        this.heapHashMap = FileHandling.loadHeapInHashMap(heapPath);
        this.roots= FileHandling.loadRoots(rootsPath);
        FileHandling.linkNodes(pointersPath,heapHashMap);
    }

    public  ArrayList<Node> copyGC() {
        ArrayList<Node> activeNodes = new ArrayList<>();
        for (int i = 0; i < roots.size(); i++) {
            heapHashMap.get(roots.get(i)).setMarked(true);
            activeNodes.add(heapHashMap.get(roots.get(i)));
        }
        int index = 0;
        while (index < activeNodes.size()) {
            int count = 0;
            ArrayList<Node> children = activeNodes.get(index++).getChildren();
            while (count < children.size()) {
                Node temp = children.get(count);
                if (temp.isMarked() == false) {
                    children.get(count).setMarked(true);
                    activeNodes.add(temp);
                }
                count++;
            }
        }
        activeNodes.get(0).setMemory_start(0);
        activeNodes.get(0).setMemory_end(activeNodes.get(0).getMemory_start() + activeNodes.get(0).getSpaceTaken() - 1);
        for (int i = 1; i < activeNodes.size(); i++) {
            activeNodes.get(i).setMemory_start(activeNodes.get(i - 1).getMemory_end() + 1);
            activeNodes.get(i).setMemory_end(activeNodes.get(i - 1).getMemory_end() + activeNodes.get(i).getSpaceTaken());
        }

        return activeNodes;
    }

    public void print(ArrayList<Node> activeNodes ){
        for (int i = 0; i < activeNodes.size(); i++) {
            System.out.println(activeNodes.get(i).getId() + "   " + activeNodes.get(i).getMemory_start() + "    " + activeNodes.get(i).getMemory_end());

        }
    }


}
