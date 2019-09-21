import java.net.*;

public class UDPReceiver {

	private final static int PACKETSIZE = 100 ;

	public static void main( String args[] )
	{ 
	      // Check the arguments
	      if( args.length != 1 )
	      {
	         System.out.println( "usage: UDPReceiver port" ) ;
	         return ;
	      }
	      try
	      {
	         // Convert the argument to ensure that is it valid and initialize variables
	         int port = Integer.parseInt( args[0] ) ;
	         int clientPort = -1;
	         InetAddress client = null;
	         String message = null;
	         String reply = null;
	         byte[] data = null;
	         // Construct the socket
	         DatagramSocket socket = new DatagramSocket( port ) ;
	         
	         for( ;; )
	         {
	        	 System.out.println( "Receiving on port " + port ) ;
		         DatagramPacket packet = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE ) ;
	             socket.receive( packet ) ;
	             
	             message = new String(packet.getData()).trim();
	             reply = ("ACK:"+message);
	             client = packet.getAddress();
	             clientPort = packet.getPort();
	             System.out.println( "Received: " +message+ "\nFrom: " +client+ " " +clientPort) ;
	             System.out.println("Pausing for 100ms and sending acknowledgement");
	             Thread.sleep(100);
	             
	             data = reply.getBytes();
	             DatagramPacket ackPacket = new DatagramPacket(data, data.length, client, clientPort);
	             socket.send(ackPacket);
	         }
	     }
	     catch( Exception e )
	     {
	        System.out.println( e ) ;
	     }
	     
  }
}
