import java.util.ArrayList;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

/**
 * Created by densh on 14.11.2016.
 */
public class TypeAnalyzer {

    public ArrayList<Node> printNodes = new ArrayList<>();

    public Node analyzeToken(String analyze) {
        if (!analyze.equals("")) {
            return new Node(new Token(analyze));
        }
        return new Node(new Token(null));
    }

    public String isInInput(String input, String[] lexemes) {
        String s = "";
        for (int i = 0; i < lexemes.length; i++) {
            if (input.contains(lexemes[i])) {
                s = lexemes[i];
                break;
            }
        }
        return s;
    }


    public TokenType getTokenType(Token token, TokenType t) {
        if (token.getData() != null)
            return t;
        else
            return null;
    }

    public String swapHelp(String result, String start) {
        if (!result.equals("")) {
            String buff = start;
            String s;
            try {
                s = buff.replaceFirst(result, "~");
            } catch (PatternSyntaxException e) {
                s = buff.replaceFirst("\\" + result, "~");
            }
            return s;
        } else
            return start;
    }

    public void analyzeSymbols(String[] arr, Map<Node, TokenType> map, ArrayList<Node> nodes) {
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals("")) {
                for (int j = 0; j < Repository.keyWords.length; j++) {
                    if (arr[i].equals(Repository.keyWords[j])) {
                        Node node = new Node(new Token(arr[i]));
                        nodes.add(node);
                        map.put(node, TokenType.KEYWORD);
                        arr[i] = "~";
                    }
                }
                for (int j = 0; j < Repository.validCharSymbols.length; j++) {
                    if (arr[i].charAt(0) == Repository.validCharSymbols[j]) {
                        Node node = new Node(new Token(arr[i]));
                        nodes.add(node);
                        map.put(node, TokenType.VARIABLE);
                        arr[i] = "~";
                    }
                }
                for (int j = 0; j < Repository.validCharSymbols.length; j++) {
                    if (arr[i].charAt(arr[i].length() - 1) == Repository.validCharSymbols[j]) {
                        Node node = new Node(new Token(arr[i]));
                        nodes.add(node);
                        map.put(node, TokenType.VARIABLE);
                        arr[i] = "~";
                    }
                }
            }
        }
    }

    public void buildRelations(ArrayList<Node> nodes, Map<Node, TokenType> map) {
        Node[] nodes1 = new Node[nodes.size() - 1];
        nodes1 = nodes.toArray(nodes1);
        nodes1[0].setFree(true);
        if (nodes1[0].isFree()) {
            nodes1[0].setFree(true);
            relationsTree(0, nodes1, map);
        }
        (new Analyzer()).buildTree(nodes1[0]);
    }

    private void relationsTree(int startPosition, Node[] nodes, Map<Node, TokenType> map) {
        for (int position = startPosition; position < nodes.length; position++) {

        }
    }

    private void toTheRight(int j, int position, Node[] nodes, Map<Node, TokenType> map) {
        if (nodes[j].isFree()) {
            nodes[j].setFree(false);
            nodes[position].setNextNode(nodes[j]);
            relationsTree(j, nodes, map);
        } else {
            nodes[position].setNextNode(new Node(new Token("~")));
        }
    }

    private void toTheLeft(int j, int position, Node[] nodes, Map<Node, TokenType> map) {
        if (nodes[j].isFree()) {
            nodes[j].setFree(false);
            nodes[position].setPreviousNode(nodes[j]);
            relationsTree(j, nodes, map);
        } else {
            nodes[position].setPreviousNode(new Node(new Token("~")));
        }
    }
}
