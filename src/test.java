public class test {
    public static void main(String[] args){

        PageContent pageContent = new PageContent();
        Connection connection = new Connection();

        try {

//            String pagecontent = pageContent.connection("https://www.google.com/");
//            String postparms = pageContent.getFormParams(pagecontent, "BlackClover");
//
//
//            pageContent.sendPost("https://www.google.com/", postparms);
//
//            //String result = pageContent.connection();

            connection.connect("https://www.google.com/search?q=hunter+x+hunter");

        }catch (Exception ex){
            System.out.println("Something went wrong");
        }
    }
}
