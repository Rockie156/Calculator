package States;

import Expressions.Expression;
import Expressions.Operator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

public abstract class State {
    public String value;
    public HashMap<Character, Supplier<State>> transitionMap;
    public ArrayList<Character> tokens = new ArrayList<>();
    public State parent;
    public Expression operand1 = null;
    public Expression operand2 = null;
    public Operator operator = null;

    /**
     * Super constructor. Each state in the DFA keeps track of
     * its parent in linked-list fashion.
     *
     * @param parent
     */
    public State(State parent) {
        this.parent = parent;
        // Assign operators if parent is not null
        // and parent was not "Final" state
        if (parent != null && !(parent instanceof Calculate)) {
            this.operand1 = parent.operand1;
            this.operand2 = parent.operand2;
            this.operator = parent.operator;
        }
        transitionMap = new HashMap<>();
        putKeys("C", () -> new Start(null));
    }

    /**
     * Gets the next state from the current state with current token
     */
    public State getNext(Character token) {
        // Invoke supplier to get an object
        // transitionMap is our set of transition functions
        State next = transitionMap.get(token).get();

        // Creates the token structure
        // Edge case for when parent is null (next = start(null))
        if (next.parent != null) {
            next.tokens = next.parent.tokens;
        } else {
            next.tokens = new ArrayList<>();
        }

        // Add the current input token
        next.addToken(token);

        return next;
    }

    /**
     * Returns the token associated with the current state
     *
     * @return
     */
    public String getToken() {
        return tokens.get(tokens.size() - 1).toString();
    }


    /**
     * Adds a token
     *
     * @param token
     */
    public void addToken(Character token) {
        tokens.add(token);
    }

    /**
     * Discards the last token. Used for recovering from the error state
     */
    public void discard() {
        tokens.remove(tokens.size() - 1);
    }

    /**
     * Iterates through a string and adds a map of each character
     * in tokens to the given state
     *
     * @param tokens        a string containing the tokens without delimiter
     * @param stateSupplier the resulting state
     */
    public void putKeys(String tokens, Supplier<State> stateSupplier) {
        for (int i = 0; i < tokens.length(); i++) {
            transitionMap.put(tokens.charAt(i), stateSupplier);
        }
    }

    /**
     * Represents the change in state for the inputHandler
     *
     * @param expressions The current state
     * @return The resulting state
     */
    public abstract Expression[] changeTree(Expression[] expressions);

    public abstract void setExpressions();
}
