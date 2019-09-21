# Source: https://pymotw.com/2/socket/udp.html
# 
# Script is currently not functional.

import socket, sys, time

textport = sys.argv[1]

sr = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
port = int(textport)
server_address = ('127.0.0.1', port)
sr.bind(server_address)

while True:

    print ("Waiting to receive on port %d : press Ctrl-C or Ctrl-Break to stop " % port)

    buf, address = sr.recvfrom(port)
    if not len(buf):
        break
    print ("Received %s bytes from %s %s: " % (len(buf), address, buf ))
    
    time.sleep(.1)
    print("Sending acknowledgement")
    s.connect(address)
    message = "ACK:" +str(buf)
    data = message.strip()
    if not len(data):
        break
    s.sendall(data.encode('utf-8'))
    s.sendto(data.encode('utf-8'), address)

s.shutdown(1)
