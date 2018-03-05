import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class EchoServer{

    val LISTEN_POST = 4500

    init {
        println("Kotlin Minimum Echo Server Started!")
    }

    fun run(){
        val socket = ServerSocket(LISTEN_POST)
        val acceptSocket = socket.accept()

        handleClient(acceptSocket)
    }

    fun handleClient(acceptSocket:Socket){
        val input = acceptSocket.getInputStream()
        val output = acceptSocket.getOutputStream()

        val writer = PrintWriter(output,true)
        val bufferReader = BufferedReader(InputStreamReader(input))

        while (true){
            val line = bufferReader.readLine()?:break

            if(line.isEmpty()){
                println("Echo Server received data: (EmptyData) dont write it")
            }else{
                println("Echo Server received data: $line")
                writer.write("$line\n")
                writer.flush()
            }
            if (line=="server -e") break
        }
        bufferReader.close()
        writer.close()
        println("Server connection closed")
        acceptSocket.close()
    }
}