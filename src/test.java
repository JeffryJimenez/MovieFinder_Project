public class test {
    public static void main(String[] args){

//        DataBaseSearcher dataBaseSearcher = new DataBaseSearcher();
        Connection connection = new Connection("Demon Slayer");

        try {


//            System.out.println(dataBaseSearcher.fetch("A Cop (1972)"));
            connection.connect();


        }catch (Exception ex){
            System.out.println("Something went wrong");
        }
    }
}
