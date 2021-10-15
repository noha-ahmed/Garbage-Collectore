import java.util.ArrayList;
import java.util.HashMap;

public class MarkAndCompactGCMain {
    public static void main ( String[] args ){

        // 0 heap - 1 roots - 2 pointers - 3 newHeap
        /*
        String[] arg = new String[4];
        arg[0] = "/home/noha/IdeaProjects/GarbageCollector/test 2/heap.csv";
        arg[1] = "/home/noha/IdeaProjects/GarbageCollector/test 2/root.txt";
        arg[2] =  "/home/noha/IdeaProjects/GarbageCollector/test 2/pointers.csv";
        arg[3] = "/home/noha/IdeaProjects/GarbageCollector/test 2/markAndCompact_newHeap.csv";
         */

        if( args.length != 4 ){
            System.out.println("Invalid number of arguments!");
            return;
        }

        MarkAndCompactGC garbageCollector = new MarkAndCompactGC(args[0], args[1] , args[2] );
        FileHandling.storeHeap(garbageCollector.markAndCompactGC() , args[3]);
    }
}

