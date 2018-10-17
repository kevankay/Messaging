package com.capgemini.demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.QueueConnectionFactory;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sun.messaging.Queue;




public class App 
{
    public static void main( String[] args ) {
   /* {
    	if(args.length == 0) {
        System.out.println( "Must supply a message");
        System.out.println( "From maven run with: maven - pargs=\"Hello World\" run");
        return;
     }*/ 
    	
    	ConnectionFactory connectionFactory = null;
    	Connection connection = null;
    	Queue queue = null;
    	
    	try {
			InitialContext initialContext=new InitialContext();
			queue = (Queue) initialContext.lookup("jms/P2PQueue");
			connectionFactory = (QueueConnectionFactory)
					initialContext.lookup("jms/__defaultConnectionFactory");
			
    	} catch (NamingException e) {
			e.printStackTrace();
    	}
    	
    	try(JMSContext context = connectionFactory.createContext()){
    		TextMessage message=context.createTextMessage("Hello...");
    		context.createProducer().send(queue, message);
    	}
}
}