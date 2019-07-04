public class test {
    public static void main(String[] args){

        PageContent pageContent = new PageContent();

        try {

            pageContent.getFormParams("adwuhhawd", "email", "password");
        }catch (Exception ex){
            System.out.println("Something went wrong");
        }
    }
}
