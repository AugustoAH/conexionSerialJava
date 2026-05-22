
package com.mycompany.serial_led;

import com.fazecast.jSerialComm.SerialPort;
import java.io.PrintWriter;

public class ConexionArduino {
    
    public static void main(String[] args) throws InterruptedException{
        // 1. Obtener la lista de puetos disponibles
        SerialPort [] puertos = SerialPort.getCommPorts();
        
        if(puertos.length == 0){
            System.out.println("No se encontraron Puertos OJO..");
            return;
        }
        // Aquí Seleccionamos el primer puerto disponible
        // Por que solo hay un dispositivo Arduino Conectado
        // Ojito, Si tiene varios dispositivos conectados
        // necesita cambiar el indice del arreglo.
        // Tarea: Debe Utilizar un ComboBox Para facilitar el Cambio.
        SerialPort puerto = puertos[0];
        System.out.println("Conectado a: "+puerto.getSystemPortName());
        
        // 2. Configuramos el puerto, debe coincidir con el Serial.Begin 
        //del arduino tasa de transferencia 9600
        puerto.setComPortParameters(9600, 8, 1, 0);
        puerto.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        
        // 3. Abrimos el Puerto
        if(puerto.openPort()){
            System.out.println("Puerto Abierto para Trabajar");
        }else{
            System.out.println("Error, Puerto Ocupaditoooo");
            return;
        }
        
        // 4. Enviamos Datos al Arduino
        // Usamos un PrintWriter para facilitar el envio de texto
        PrintWriter output = new PrintWriter(puerto.getOutputStream());
        
        // Pausar 2 segundos para permitir que el arduino se 
        //reinicie tras la conexion
        
        for (int i = 0; i < 10; i++) {
             Thread.sleep(2000);
        
        System.out.println("Enviando comando para encender LED");
        output.print("1");
        output.flush(); // Fuerza el envio de los datos al Arduino
        
        Thread.sleep(5000);
        
        System.out.println("Enviando comando para apagar LED");
        output.print("2");
        output.flush(); // Fuerza el envio de los datos al Arduino
        }
       
        
        if (puerto.closePort()) {
            System.out.println("Puerto Cerrado Correctamente yupi...");
        }
    }
    
    
    
    
}
