import java.util.ArrayList;

public class Node {
     int id;
     int spaceTaken;
     int memory_start;
     int memory_end;
     ArrayList<Node> children=new ArrayList<>();
     boolean marked;
     Node(int id,int mem_start,int mem_end){
          this.id=id;
          this.memory_start=mem_start;
          this.memory_end =mem_end;
          this.spaceTaken=mem_end-mem_start+1;
          marked=false;
     }

     public int getId() {
          return id;
     }

     public int getMemory_end() {
          return memory_end;
     }

     public void setMemory_end(int memory_end) {
          this.memory_end = memory_end;
     }

     public int getMemory_start() {
          return memory_start;
     }

     public void setMemory_start(int memory_start) {
          this.memory_start = memory_start;
     }

     public int getSpaceTaken() {
          return spaceTaken;
     }

     public ArrayList<Node> getChildren() {
          return children;
     }

     public void addChild(Node child) {
          this.children.add(child);
     }

     public boolean isMarked() {
          return marked;
     }

     public void setMarked(boolean marked) {
          this.marked = marked;
     }

}
