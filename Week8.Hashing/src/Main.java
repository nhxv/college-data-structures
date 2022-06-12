public class Main {
    public static void main(String[] args) {
        {
            // first set of tests --------------------
            // FHhashQPwFind< Integer, EmployeeCompInt> hashTable
            //    = new FHhashQPwFind<Integer, EmployeeCompInt>();

            FHhashQPwFind<String, EmployeeCompString> hashTable
                    = new FHhashQPwFind<String, EmployeeCompString>(10);

            // EmployeeCompInt
            //  e1 = new EmployeeCompInt(
            //        new Employee("peter pan", 1) ),
            //  e2 = new EmployeeCompInt(
            //        new Employee("alice wonderland", 2) ),
            //  e3 = new EmployeeCompInt(
            //         new Employee("captain hook", 3) );

            EmployeeCompString
                    e1 = new EmployeeCompString(
                    new Employee("peter pan", 1)),
                    e2 = new EmployeeCompString(
                            new Employee("alice wonderland", 2)),
                    e3 = new EmployeeCompString(
                            new Employee("captain hook", 3));

            // EmployeeCompInt empSearchResult;
            EmployeeCompString empSearchResult;

            if (hashTable.insert(e1))
                System.out.println("inserted " + e1);
            if (hashTable.insert(e1))
                System.out.println("inserted " + e1);
            if (hashTable.insert(e2))
                System.out.println("inserted " + e2);
            if (hashTable.insert(e2))
                System.out.println("inserted " + e2);
            if (hashTable.insert(e3))
                System.out.println("inserted " + e3);
            if (hashTable.insert(e3))
                System.out.println("inserted " + e3);

            System.out.println(hashTable.size());

            try {
                empSearchResult = hashTable.find("alice wonderland");
                //empSearchResult = hashTable.find(2);
                System.out.println("Found " + empSearchResult);
            } catch (Exception e) {
                System.out.println("Not found");
            }

            if (hashTable.remove(e1))
                System.out.println("removed " + e1);
            if (hashTable.remove(e1))
                System.out.println("removed " + e1);
            if (hashTable.remove(e2))
                System.out.println("removed " + e2);
            if (hashTable.remove(e2))
                System.out.println("removed " + e2);
            if (hashTable.remove(e3))
                System.out.println("removed " + e3);
            if (hashTable.remove(e3))
                System.out.println("removed " + e3);
            System.out.println(hashTable.size());

            if (hashTable.contains(e3))
                System.out.println("contains " + e3);

            // second set of tests --------------------

            FHhashQPwFind hashTable2 = new FHhashQPwFind(10);
            String substrate =
                    "asdlkfj asdoiBIUYVuwer slkdjLJfwoe89)B)(798rjMG0293lkJLJ42lk3j)(*";
            String[] strArray = new String[500];
            String strSearchResult;
            int k, limit;

            substrate = substrate + substrate;

            for (k = 0; k < substrate.length() - 6; k++)
                strArray[k] = substrate.substring(k, k + 5);
            limit = k;

            hashTable2.setMaxLambda(.5);
            for (k = 0; k < limit; k++)
                if (hashTable2.insert(strArray[k]))
                    System.out.println("inserted #" + k + " " + strArray[k]);
            System.out.println("\n#strings generated: " + limit
                    + "\n#elements in ht: " + hashTable2.size());

            try {
                //strSearchResult = hashTable2.find("Rene Descartes");
                strSearchResult = (String) hashTable2.find("MG029");
                System.out.println("Found " + strSearchResult);
            } catch (Exception e) {
                System.out.println("Not found");
            }
        }
    }
}
