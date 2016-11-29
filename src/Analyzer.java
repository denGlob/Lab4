import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by densh on 13.11.2016.
 */
public class Analyzer {

    private ArrayList<Node> nodes;
    private LinkedHashMap<Node, TokenType> nodeType;

    public Analyzer() {
        nodes = new ArrayList<>();
        nodeType = new LinkedHashMap<>();
    }

    public void analyzeInputString(String input) {
        TypeAnalyzer typeAnalyzer = new TypeAnalyzer();
        String swap = input.replace(" ", "");
        String res = "";
        String indicator = swap;
        while (swap.length() != 0) {
            indicator = swap;

            res = typeAnalyzer.isInInput(swap, Repository.validOperSymbols);
            Node nodeOperator = typeAnalyzer.analyzeToken(res);
            if (nodeOperator.getToken().getData() != null) {
                nodes.add(nodeOperator);
                nodeType.put(nodeOperator, typeAnalyzer.getTokenType(nodeOperator.getToken(), TokenType.OPERATION));
            }
            swap = typeAnalyzer.swapHelp(res, swap);

            res = typeAnalyzer.isInInput(swap, Repository.validLogicOperSymbols);
            Node nodeOperatorL = typeAnalyzer.analyzeToken(res);
            if (nodeOperatorL.getToken().getData() != null) {
                nodes.add(nodeOperatorL);
                nodeType.put(nodeOperatorL, typeAnalyzer.getTokenType(nodeOperatorL.getToken(), TokenType.LOGICAL_OPERATION));
            }
            swap = typeAnalyzer.swapHelp(res, swap);

            res = typeAnalyzer.isInInput(swap, Repository.validComparator);
            Node nodeComparator = typeAnalyzer.analyzeToken(res);
            if (nodeComparator.getToken().getData() != null) {
                nodes.add(nodeComparator);
                nodeType.put(nodeComparator, typeAnalyzer.getTokenType(nodeComparator.getToken(), TokenType.COMPARATOR));
            }
            swap = typeAnalyzer.swapHelp(res, swap);

            res = typeAnalyzer.isInInput(swap, Repository.validSeparatorCircle);
            Node nodeSeparatorC = typeAnalyzer.analyzeToken(res);
            if (nodeSeparatorC.getToken().getData() != null) {
                nodes.add(nodeSeparatorC);
                nodeType.put(nodeSeparatorC, typeAnalyzer.getTokenType(nodeSeparatorC.getToken(), TokenType.SEPARATOR_CIRCURAL));
            }
            swap = typeAnalyzer.swapHelp(res, swap);


            res = typeAnalyzer.isInInput(swap, Repository.validSeparatorSquare);
            Node nodeSeparatorS = typeAnalyzer.analyzeToken(res);
            if (nodeSeparatorS.getToken().getData() != null) {
                nodes.add(nodeSeparatorS);
                nodeType.put(nodeSeparatorS, typeAnalyzer.getTokenType(nodeSeparatorS.getToken(), TokenType.SEPARATOR_SQUARE));
            }
            swap = typeAnalyzer.swapHelp(res, swap);

            res = typeAnalyzer.isInInput(swap, Repository.validSeparatorWave);
            Node nodeSeparatorW = typeAnalyzer.analyzeToken(res);
            if (nodeSeparatorW.getToken().getData() != null) {
                nodes.add(nodeSeparatorW);
                nodeType.put(nodeSeparatorW, typeAnalyzer.getTokenType(nodeSeparatorW.getToken(), TokenType.SEPARATOR_WAVE));
            }
            swap = typeAnalyzer.swapHelp(res, swap);

            res = typeAnalyzer.isInInput(swap, new String[]{";"});
            Node nodeSeparatorE = typeAnalyzer.analyzeToken(res);
            if (nodeSeparatorE.getToken().getData() != null) {
                nodes.add(nodeSeparatorE);
                nodeType.put(nodeSeparatorE, typeAnalyzer.getTokenType(nodeSeparatorE.getToken(), TokenType.SEPARATOR_EXIT));
            }
            swap = typeAnalyzer.swapHelp(res, swap);

            if (indicator.equals(swap))
                break;
        }

        String[] arr = swap.split("~");

        typeAnalyzer.analyzeSymbols(arr, nodeType, nodes);

        typeAnalyzer.buildRelations(nodes, nodeType);
//        buildTree(nodes.get(0));
//        buildTree(typeAnalyzer.printNodes.get(0));
        System.out.println();
    }

    public void buildTree(Node node) {
        if (checkBracket(nodes, "{", "}") && checkBracket(nodes, "(", ")")) {
            node.printTree();
        }
    }
    public boolean checkBracket(ArrayList<Node> nodes, String bracket1, String bracket2) {
        int k = 0;
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getToken().getData().equals(bracket1)) k++;
            if (nodes.get(i).getToken().getData().equals(bracket2)) k--;
        }
        if (k != 0) return false;
        return true;
    }
}
