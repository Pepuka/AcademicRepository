import java.net.*;
//import java.util.Scanner;

public class UDPSender {

	public static void main(String[] args) 
   {
	      // Check the arguments
	      if( args.length != 3 )
	      {
	         System.out.println( "usage: java UDPSender host port number" ) ;
	         System.out.println("the number must be a non-negative integer");
	         return ;
	      }
	      DatagramSocket socket = null ;
	      try
	      {
	         // Convert the arguments first, to ensure that they are valid
	         InetAddress host = InetAddress.getByName( args[0] ) ;
	         int port = Integer.parseInt( args[1] ) ;
	         int num = Integer.parseInt(args[2]);
	         socket = new DatagramSocket() ;
	         
	         // Scanner is never used in this version
	         //Scanner in;
	         //in = new Scanner (System.in);
	         String message = null;
	         byte[] data = null;
	         DatagramPacket packet = null;
	         System.out.println("Sending " +num+ " messages...");
	         for(int i = 1; i < (num+1); i++)
	         {
	        	 // Sending the message
	        	 message = ("Message" +i);
	        	 data = message.getBytes();
	        	 packet = new DatagramPacket(data, data.length, host, port);
	        	 socket.send(packet);
	        	 System.out.println("Sent: " +message);
	        	 
	        	 // Waiting for a reply before looping
	        	 DatagramPacket ackPacket = new DatagramPacket(new byte[104], 104);
	        	 socket.receive(ackPacket);
	        	 System.out.println("Received: " +new String(ackPacket.getData()).trim()+ "\nFrom: " +ackPacket.getAddress()+ " " +ackPacket.getPort());
	         }
	         /*
	         while (true)
	         {
	        		 System.out.println("Enter text to be sent, ENTER to quit ");
	        		 message = in.nextLine();
	        		 if (message.length()==0) break;
	        		 byte [] data = message.getBytes() ;
	        		 DatagramPacket packet = new DatagramPacket( data, data.length, host, port ) ;
	        		 socket.send( packet ) ;
	         }
	         */ 
	         System.out.println ("Closing down");
	      }
	      catch( Exception e )
	      {
	         System.out.println( e ) ;
	      }
	      finally
	      {
	         if( socket != null )
	            socket.close() ;
      }
   }
}
