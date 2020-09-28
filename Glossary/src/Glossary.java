import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Inputs user text file and outputs a group of HTML files that format into a
 * glossary.
 *
 * @author Jonathan Chemaly
 */
public final class Glossary {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    /**
     * Takes the input files and makes a queue of them and their definition.
     *
     * @param wordDefinition
     *            a map that holds the word with is definition
     * @param in
     *            the text file from the user to be read
     * @return a queue of terms
     */
    private static Queue<String> termsWithDefinitions(
            Map<String, String> wordDefinition, SimpleReader in) {
        Queue<String> terms = new Queue1L<>();

        while (!in.atEOS()) {
            String word = in.nextLine();
            String definition = in.nextLine();
            String temp = " ";

            //checks for multiple line definition
            while (!in.atEOS() && temp.length() > 0) {
                temp = in.nextLine();
                if (temp.length() > 0) {
                    definition += temp;
                }
            }
            wordDefinition.add(word, definition);
            terms.enqueue(word);
        }
        return terms;
    }

    /**
     * Checks the definition of each word and adds a hyperlink to another word's
     * definition if it is found within the definition of the given word.
     *
     * @param wordDefinition
     *            a map that holds the word with is definition
     * @param terms
     *            the queue of all the terms in the glossary
     * @param out
     *            the files inputed by the user
     */
    private static void placeIntextLinks(Map<String, String> wordDefinition,
            Queue<String> terms, String out) {
        Queue<String> temp = new Queue1L<>();

        Set<Character> specialChars = new Set1L<>();
        specialChars.add(' ');
        specialChars.add('.');
        specialChars.add(',');

        int position = 0;
        //checks each term
        while (terms.length() > 0) {
            String term = terms.dequeue();
            temp.enqueue(term);
            String definition = wordDefinition.value(term);
            String finalDefinition = " ";

            while (position < definition.length()) {
                String tempT = nextWordOrSeparator(definition, position,
                        specialChars);
                //checks if word has its own definition is so adds link
                if (wordDefinition.hasKey(tempT)) {
                    finalDefinition += "<a href=\"" + tempT + ".html\">" + tempT
                            + "</a>";
                } else {
                    finalDefinition += tempT;
                }
                position = position + tempT.length();
            }
            wordDefinition.replaceValue(term, finalDefinition.substring(1));

            position = 0;
        }
        terms.transferFrom(temp);
    }

    /**
     * Creates the correct HTML text that links the words and definitions.
     *
     * @param wordDefinition
     *            map of strings where <term,term's definition>
     * @param terms
     *            a queue that contains all the terms
     * @param out
     *            the files inputed by the user
     */
    private static void makeHTMLFiles(Map<String, String> wordDefinition,
            Queue<String> terms, String out) {
        Queue<String> temp = new Queue1L<>();

        while (terms.length() > 0) {
            String term = terms.dequeue();
            String definition = wordDefinition.value(term);
            temp.enqueue(term);

            //creates the HTML file for each term
            SimpleWriter fileOut = new SimpleWriter1L(
                    out + "/" + term + ".html");
            fileOut.println(
                    "<html> <head> <title>" + term + "</title> </head><body>");
            fileOut.println("<h2><b><i><font color=\"red\">" + term
                    + "</font></i></b></h2>");
            fileOut.println("<blockquote>" + definition + "</blockquote>");
            fileOut.println(
                    "<hr /><p>Return to <a href=\"index.html\">index</a>.</p>");
            fileOut.println("</body></html>");
            fileOut.close();
        }
        terms.transferFrom(temp);

    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position} This is from Homework 21
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        int count = 0;
        char resultPiece = 'a';
        String result = "";

        //finds first occurence of seperator
        if (separators.contains(text.charAt(position))) {

            while (count < text.substring(position, text.length()).length()) {
                resultPiece = text.charAt(position + count);

                if (separators.contains(text.charAt(position + count))) {
                    result = result + resultPiece;
                    count++;

                } else {
                    count = text.substring(position, text.length()).length();
                }
            }
            count = 0;

        } else {

            while (count < text.substring(position, text.length()).length()) {
                resultPiece = text.charAt(position + count);

                if (!separators.contains(text.charAt(position + count))) {
                    result = result + resultPiece;
                    count++;

                } else {
                    count = text.substring(position, text.length()).length();
                }
            }
            count = 0;
        }
        return result;

    }

    /**
     * Creates the index HTML file for the glossary.
     *
     * @param htmlOut
     *            SimpleWriter that outputs to the specific file
     * @param terms
     *            the queue of all the terms in the glossary
     *
     */
    private static void makeHTMLIndex(SimpleWriter htmlOut,
            Queue<String> terms) {
        htmlOut.println("<html><head><title>Glossary</title></head>");
        htmlOut.println("<body><h2>Glossary</h2><hr /><h3>Index</h3><ul>");

        //goes through each term and adds the correct link
        while (terms.length() > 0) {
            String term = terms.dequeue();
            htmlOut.println(
                    "<li><a href=\"" + term + ".html\">" + term + "</a></li>");
        }
        htmlOut.println("</ul></body></html>");
    }

    /**
     * A comparator method that returns the comparison between two string
     * alphabetically.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader userIn = new SimpleReader1L();
        SimpleWriter userOut = new SimpleWriter1L();

        userOut.print("Enter an input file: ");
        String input = userIn.nextLine();
        userOut.print("Enter an output folder: ");
        String output = userIn.nextLine();

        SimpleReader in = new SimpleReader1L(input);
        SimpleWriter htmlOut = new SimpleWriter1L(output + "/index.html");
        Map<String, String> wordDefinition = new Map1L<>();
        Queue<String> terms = new Queue1L<>();

        terms.append(termsWithDefinitions(wordDefinition, in));

        Comparator<String> alphabetize = new StringLT();
        terms.sort(alphabetize);

        placeIntextLinks(wordDefinition, terms, output);

        makeHTMLFiles(wordDefinition, terms, output);

        makeHTMLIndex(htmlOut, terms);

        userIn.close();
        userOut.close();
        htmlOut.close();
    }

}
