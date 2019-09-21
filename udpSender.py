# Source: https://pymotw.com/2/socket/udp.html
# 
# Script is currently not functional.  Original code that was removed 
# has been commented out

import socket, sys, time

host = sys.argv[1]
textport = sys.argv[2]
num = int(sys.argv[3])

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sr = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
port = int(textport)
server_address = (host, port)
s.connect(server_address)

for i in range(1, (num+1)):
    message = "Message" +str(i)
    data = message.strip()
    if not len(data):
        break
    s.sendall(data.encode('utf-8'))
    s.sendto(data.encode('utf-8'), server_address)
    print("Sent: %s" % message)

    buf, address = s.recvfrom(port)
    if not len(buf):
        break
    print("Received: %s \nFrom: %s" % buf, address)

#while 1:
#    print ("Enter data to transmit: ENTER to quit")
#    data = sys.stdin.readline().strip()
#    if not len(data):
#        break
#    s.sendall(data.encode('utf-8'))
#    s.sendto(data.encode('utf-8'), server_address)

s.shutdown(1)
