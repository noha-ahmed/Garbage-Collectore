import java.util.ArrayList;
import java.util.HashMap;

public class CopyGCMain {

    public static void main ( String[] args ){
        if( args.length != 4 ){
            System.out.println("Invalid number of arguments!");
            return;
        }
        // 0 heap - 1 roots - 2 pointer - 3 newHeap
        CopyGC garbageCollector = new CopyGC( args[0], args[1] , args[2] );
        FileHandling.storeHeap(garbageCollector.copyGC() , args[3]);
    }
}
