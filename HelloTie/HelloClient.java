import HelloApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class HelloClient{

  public static void main(String args[]){
  
    try{
      // create and initialize the ORB
      ORB orb = ORB.init(args, null);

      // get the root naming context
      org.omg.CORBA.Object objRef = 
	  orb.resolve_initial_references("NameService"); //get_initial_references로 변경
	  
      // Use NamingContextExt instead of NamingContext. This is 
      // part of the Interoperable naming Service.  
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef); //narrow를 casting으로 변경
 
      // resolve the Object Reference in Naming
      String name = "Hello";
      Hello helloImpl = HelloHelper.narrow(ncRef.resolve_str(name)); //narrow 보다 NamingObject으로

      System.out.println("Obtained a handle on server object: " + helloImpl);
      System.out.println(helloImpl.sayHello());
      helloImpl.shutdown();
      }
      
    catch (Exception e) {
      System.out.println("ERROR : " + e) ;
      e.printStackTrace(System.out);
    }
  }
}
