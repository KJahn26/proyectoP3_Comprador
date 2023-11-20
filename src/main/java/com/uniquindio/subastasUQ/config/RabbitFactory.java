package com.uniquindio.subastasUQ.config;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitFactory {
    private ConnectionFactory connectionFactory;

    public RabbitFactory(){
        this.connectionFactory= new ConnectionFactory();
        this.connectionFactory.setHost("localhost");
        this.connectionFactory.setPort(5672);
        this.connectionFactory.setUsername("proyectoProgramacion");
        this.connectionFactory.setPassword("programacion3");
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }
}
