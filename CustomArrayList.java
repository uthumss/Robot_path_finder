package pathPlot;

public class CustomArrayList {
    private Node[] arr;
    private int capacity; // How many values can be stored
    private int current; // How many values are stored

    // Constructor
    public CustomArrayList(){
        arr = new Node[1];
        capacity=1;
        current=0;
    }

    // Defining methods to be used
    public void add(Node data){
        if (current == capacity){
            Node[] temp = new Node[capacity *2];
            for (int i=0; i<capacity; i++){
                temp[i]=arr[i];
            }
            capacity *= 2;
            arr=temp;
        }
        arr[current]=data;
        current++;

    }

    public void remove(Node data){
        int index= -1;
        for(int i=0; i<current; i++){
            if( arr[i]==data){
                index=i;
                break;
            }
        }
        if (index != -1){
            for(int i=index;i<current-1; i++){
                arr[i] = arr[i+1];
            }
            current--;
        }
    }

    public Node get(int index){
        if (index >= 0 && index < current){
            return arr[index];
        }
        else {
            return null;
        }
    }

    public int size(){
        return current;
    }
}
