package MathsVis.interpreter;

public class ErrorLog {

    private StringBuilder errorLog;

    public ErrorLog() {
        errorLog = new StringBuilder();
    }

    public void append(String append) {
        errorLog.append(append).append("\n");
    }

    public void append(String string, int index, int expression) {

        errorLog.append(string);
        errorLog.append(" in column ");
        errorLog.append(index + 1);
        errorLog.append(" in expression ");
        errorLog.append(expression).append("\n");
    }

    public void appendParser(String string, int index, int expression) {

        errorLog.append(string);
        errorLog.append(" in column ");
        errorLog.append(index);
        errorLog.append(" in expression ");
        errorLog.append(expression + 1).append("\n");
    }

    public void append(double append) {
        errorLog.append(append).append("\n");
    }

    public String getMessage() {
        return errorLog.toString();
    }
}
