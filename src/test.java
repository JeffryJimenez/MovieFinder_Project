public class test {
    public static void main(String[] args){

        PageContent pageContent = new PageContent();

        try {

            String postparms = pageContent.getFormParams("adwuhhawd", "BlackClover");
            pageContent.sendPost("https://www.google.com/", postparms);


        }catch (Exception ex){
            System.out.println("Something went wrong");
        }
    }
}
