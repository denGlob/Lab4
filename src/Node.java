/**
 * Created by densh on 02.11.2016.
 */
public class Node {
    private Token token;
    private Node previousNode;
    private Node nextNode;
    private boolean free;

    public Node() {
    }

    public Node(Token token) {
        this.token = token;
    }

    public Node(Token token, Node previousNode, Node nextNode) {
        this.token = token;
        this.previousNode = previousNode;
        this.nextNode = nextNode;
        this.free = true;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public void printTree(){
        if(previousNode!=null){
            previousNode.printTree();
        }
        System.out.print(this.toString());
        if(nextNode!=null){
            nextNode.printTree();
        }
    }

    @Override
    public String toString() {
        if (!token.getData().equals("~")) {
            return token.getData();
        }
        else {
            return " ERROR!!! ";
        }
    }
}
