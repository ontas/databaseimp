package simpledb.query;

public class UnionScan implements Scan {
    private Scan s1, s2, current;

    public UnionScan(Scan scan1, Scan scan2) {
        s1 = scan1;
        s2 = scan2;
        beforeFirst();
    }

    public void beforeFirst() {
        s1.beforeFirst();
        current = s1;
    }

    public boolean next() {
        if (current.next())
            return true;
        if (current == s2)
            return false;
        current = s2;
        s2.beforeFirst();
        return s2.next();
    }

    public void close() {
        s1.close();
        s2.close();
    }

    public Constant getVal(String fldname) {
        return current.getVal(fldname);
    }

    public int getInt(String fldname) {
        return current.getInt(fldname);
    }

    public String getString(String fldname) {
        return current.getString(fldname);
    }

    public boolean hasField(String fldname) {
        return current.hasField(fldname);
    }

    public boolean isNull(String fldname) {
        return current.isNull(fldname);
    }
}


