import java.util.Stack;


public class App {
  
    public static int oncelik(char c){
        switch(c){
            case '+':
            case '-':
             return 1;
            case '*':
            case '/':
             return 2;
            case '^':
             return 3; 
        }
        return -1;
    } 
    
    public static String infix2postfix(String infixIfade){
        infixIfade=infixIfade.replaceAll("\\s", "");
        Stack <Character> y=new Stack();
        
        String postIfade = "";      

        for(int i=0; i<infixIfade.length(); i++){
            char c=infixIfade.charAt(i);
            if(c=='('){
                y.push(c);
            }
            else if(c==')'){
                postIfade=postIfade+y.pop();
                y.pop();
            }
            else if(oncelik(c)>0){
                if(!y.isEmpty() && oncelik(c)<=oncelik(y.peek()))
                postIfade=postIfade+y.pop();
                y.push(c);
            }
            else{
                postIfade=postIfade+c;
            }

            
        }
        int yiginBoyut=y.size();

        for(int i=0; i<yiginBoyut; i++){
            postIfade=postIfade+y.pop();
        }
        
        return postIfade;

    }

    public static int postfixHesapla(String postIfade){

        postIfade=postIfade.replaceAll("\\s", "");
        String spacePostIfade="";
        for(int a=0;a<postIfade.length();a++){
            char c = postIfade.charAt(a);
            if(c != ' '){
                spacePostIfade=spacePostIfade+c+" ";
            }
            
        }
        System.out.println(spacePostIfade);
        
        String [] postfixDizi=spacePostIfade.split(" ");

        Stack <String> y=new Stack <>();
        
        String e;
        int s1=0,s2=0,s3=0;

        for(int i=0;i<postfixDizi.length;i++){
           e=postfixDizi[i];
           e.trim();
           
           System.out.println("-->"+e+"<---");
           if(e.equals(""))
           continue;
           if(e.equals("+")){  
               s2=Integer.parseInt(y.pop()); 
               s1=Integer.parseInt(y.pop());
               s3=s1+s2;
               y.push(String.valueOf(s3));
           }
         else if(e.equals("-")){  
            s2=Integer.parseInt(y.pop()); 
            s1=Integer.parseInt(y.pop());
            s3=s1-s2;
            y.push(String.valueOf(s3));
        }
        else if(e.equals("*")){   
            s2=Integer.parseInt(y.pop()); 
            s1=Integer.parseInt(y.pop());
            s3=s1*s2;
            y.push(String.valueOf(s3));
        }
       else if(e.equals("/")){   
            s2=Integer.parseInt(y.pop());
            s1=Integer.parseInt(y.pop());
            s3=s1/s2;
            y.push(String.valueOf(s3));
        }
        else{
            y.push(e);
        }    
    }
        int sonuc=Integer.parseInt(y.pop());
        if(!y.isEmpty()){
          System.out.println("Postfix ifade hatali");
          return 0;
        }  

        return sonuc;
    } 


    public static void main(String[] args) throws Exception {
    String infixIfade="4*(5-(7+2))";
    System.out.println(infix2postfix(infixIfade));
    System.out.println(postfixHesapla(infix2postfix(infixIfade))); 
}
}
