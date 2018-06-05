CURR_BASE_URL=/apps/httpproxy
CP=`ls -al | tr ' ' '\n' | grep jar  | xargs -n 1 -i  echo $CURR_BASE_URL/{} | xargs echo | tr ' ' ':'`

echo $CP

## echo $CURR_BASE_URL
##java  -Djavax.net.debug=ssl,handshake -cp  $CURR_BASE_URL/http_proxy_app.jar:$CURR_BASE_URL/netty-all-4.1.15.Final.jar:$CURR_BASE_URL/fastjson-1.2.7.jar  -agentlib:jdwp=transport=dt_socket,address=192.168.2.71:5862,server=y,suspend=n   com.gwork.httpproxyserver.SimpleHttpsServer 

java  -Djavax.net.debug=ssl,handshake -cp $CP       com.gwork.httpproxyserver.SimpleSsSslServer 443 
