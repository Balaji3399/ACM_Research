import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuntimePropertyChecker {
    // Constructor
    public RuntimePropertyChecker() {}

    // Method to tokenize the given source code
    public List<String> tokenizeSourceCode(String sourceCode) {
        // Basic tokenization: splitting source code by whitespace
        String[] tokensArray = sourceCode.split("\\s+");
        List<String> tokens = new ArrayList<>();
        for (String token : tokensArray) {
            tokens.add(token);
        }
        return tokens;
    }

    // Method to trace method invocations in the given source code
    public List<String> traceMethodInvocations(String sourceCode) {
        List<String> methodInvocations = new ArrayList<>();
        // Regular expression pattern to match method invocations
        Pattern pattern = Pattern.compile("\\b\\w+\\s*\\(.*\\)\\s*;");
        Matcher matcher = pattern.matcher(sourceCode);
        while (matcher.find()) {
            methodInvocations.add(matcher.group());
        }
        return methodInvocations;
    }

    // Method to trace variable declarations in the given source code
    public List<String> traceVariableDeclarations(String sourceCode) {
        List<String> variableDeclarations = new ArrayList<>();
        // Regular expression pattern to match variable declarations
        Pattern pattern = Pattern.compile("\\b\\w+\\s+\\w+\\s*(=\\s*.*?;|;)");
        Matcher matcher = pattern.matcher(sourceCode);
        while (matcher.find()) {
            variableDeclarations.add(matcher.group());
        }
        return variableDeclarations;
    }

    // Method to check if a class or method is present in the source code
    public boolean isClassOrMethodPresent(String sourceCode, String classNameOrMethodName) {
        // Regular expression pattern to match class or method declarations
        String pattern = "(class\\s+" + classNameOrMethodName + "|\\b" + classNameOrMethodName + "\\s*\\(.*\\)\\s*\\{)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(sourceCode);
        return m.find();
    }

    // Method to perform real-time property checking on the given source code
    public boolean realTimeCheck(String sourceCode, String propertyToCheck) {
        // Basic property checking: checking if source code contains the property
        return sourceCode.contains(propertyToCheck);
    }

    // Example usage
    public static void main(String[] args) {
        RuntimePropertyChecker checker = new RuntimePropertyChecker();
        String sourceCode = "public class Example { public static void main(String[] args) { System.out.println(\"Hello, World!\"); } }";

        // Tokenize the source code
        List<String> tokens = checker.tokenizeSourceCode(sourceCode);

        // Trace method invocations
        List<String> methodInvocations = checker.traceMethodInvocations(sourceCode);

        // Trace variable declarations
        List<String> variableDeclarations = checker.traceVariableDeclarations(sourceCode);

        // Perform property check
        String propertyToCheck = "System.out.println";
        boolean result = checker.realTimeCheck(sourceCode, propertyToCheck);

        // Output results
        System.out.println("Tokens: " + tokens);
        System.out.println("Method Invocations: " + methodInvocations);
        System.out.println("Variable Declarations: " + variableDeclarations);
        System.out.println("Property '" + propertyToCheck + "' Found: " + result);

        String classNameToCheck = "Example";
        boolean classFound = checker.isClassOrMethodPresent(sourceCode, classNameToCheck);
        System.out.println("Class '" + classNameToCheck + "' Found: " + classFound);

        String methodToCheck = "main";
        boolean methodFound = checker.isClassOrMethodPresent(sourceCode, methodToCheck);
        System.out.println("Method '" + methodToCheck + "' Found: " + methodFound);
    }
}
